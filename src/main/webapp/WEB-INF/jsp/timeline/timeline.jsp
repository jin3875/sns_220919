<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="d-flex justify-content-center">
	<div class="contents-box">
		<%-- 글 작성 : 로그인된 상태에서만 보여짐 --%>
		<c:if test="${not empty userId}">
			<div class="write-box border rounded m-3">
				<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea>
				
				<%-- 이미지 업로드 아이콘 & 업로드 버튼 --%>
				<div class="d-flex justify-content-between">
					<div class="file-upload d-flex">
						<input type="file" id="file" class="d-none" accept=".gif, .jpg, .png, .jpeg">
						<a href="#" id="fileUploadBtn">
							<img src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png" width="35">
						</a>
						<div id="fileName" class="ml-2"></div>
					</div>
					<button id="writeBtn" class="btn btn-info">게시</button>
				</div>
			</div>
		</c:if>
		
		<%-- 타임라인 --%>
		<div class="timeline-box my-5">
			<c:forEach var="card" items="${cardList}">
				<%-- 카드 --%>
				<div class="card border rounded mt-3">
					<%-- 글쓴이 & 더보기 버튼 --%>
					<div class="d-flex justify-content-between p-2">
						<span class="font-weight-bold">${card.user.loginId}</span>
						<a href="#" class="more-btn" data-toggle="modal" data-target="#modal" data-post-id="${card.post.id}">
							<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
						</a>
					</div>
					
					<%-- 이미지 --%>
					<div class="card-img">
						<img src="${card.post.imagePath}" class="w-100" alt="본문 이미지">
					</div>
					
					<%-- 좋아요 --%>
					<div class="card-like m-3">
						<a href="/like/${card.post.id}" class="like-btn">
							<img src="https://www.iconninja.com/files/214/518/441/heart-icon.png" width="18" height="18" alt="empty heart">
							좋아요 10개
						</a>
					</div>
					
					<%-- 글 --%>
					<div class="card-post m-3">
						<span class="font-weight-bold">${card.user.loginId}</span>
						<span>${card.post.content}</span>
					</div>
					
					<%-- 댓글 --%>
					<div class="card-comment-desc border-bottom">
						<div class="ml-3 mb-1 font-weight-bold">댓글</div>
					</div>
					
					<%-- 댓글 목록 --%>
					<div class="card-comment-list m-2">
						<c:forEach var="commentView" items="${card.commentList}">
							<%-- 댓글 내용 --%>
							<div class="card-comment m-1">
								<span class="font-weight-bold">${commentView.user.loginId} :</span>
								<span>${commentView.comment.content}</span>
								
								<%-- 댓글 삭제 버튼 --%>
								<a href="#" class="commentDelBtn">
									<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10" height="10">
								</a>
							</div>
						</c:forEach>
						
						<%-- 댓글 쓰기 --%>
						<c:if test="${not empty userId}">
							<div class="comment-write d-flex border-top mt-2">
								<input type="text" class="form-control border-0 mr-2" placeholder="댓글 달기"> 
								<button type="button" class="comment-btn btn btn-light" data-post-id="${card.post.id}">게시</button>
							</div>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		// 파일 업로드 이미지 클릭 -> 숨겨져 있는 file을 동작시킴
		$('#fileUploadBtn').on('click', function(e) {
			e.preventDefault(); // a 태그의 스크롤 올라가는 현상 방지
			$('#file').click(); // input file을 클릭한 것과 같은 효과
		});
		
		// 사용자가 이미지를 선택했을 때 유효성 확인 및 업로드된 파일 이름 노출
		$('#file').on('change', function(e) {
			let fileName = e.target.files[0].name;
			// alert(fileName); // 파일이름.확장자
			
			// 확장자 유효성 확인
			let ext = fileName.split(".").pop().toLowerCase();
			if (ext != 'jpg' && ext != 'jpeg' && ext != 'gif' && ext != 'png') {
				alert("이미지 파일만 업로드 할 수 있습니다");
				$('#file').val(''); // 파일 태그의 실제 파일 제거
				$('#fileName').text(''); // 파일 이름 비우기
				return;
			}
			
			// 유효성 통과한 이미지는 상자에 업로드된 파일 이름 노출
			$('#fileName').text(fileName);
		});
		
		$('#writeBtn').on('click', function() {
			let content = $('#writeTextArea').val();
			let file = $('#file').val();
			
			if (content == '') {
				alert("내용을 입력하세요");
				return;
			}
			
			if (file == '') {
				alert("사진을 선택하세요");
				return;
			}
			
			let formData = new FormData();
			formData.append("content", content);
			formData.append("file", $('#file')[0].files[0]);
			
			$.ajax({
				type:"POST"
				, url:"/post/create"
				, data:formData
				, enctype:"multipart/form-data"
				, processData:false
				, contentType:false
				
				, success:function(data) {
					if (data.code == 1) {
						alert("글이 게시되었습니다");
						location.reload();
					} else {
						alert(data.errorMessage);
					}
				}
				, error:function(e) {
					alert("게시에 실패하였습니다");
				}
			});
		});
		
		// 댓글 작성
		$('.comment-btn').on('click', function() {
			let postId = $(this).data('post-id');
			let content = $(this).siblings('input').val().trim(); // 지금 클릭된 게시버튼의 형제인 input 태그를 가져온다.
			
			if (content == '') {
				alert("댓글을 입력하세요");
				return;
			}
			
			$.ajax({
				type:"POST"
				, url:"/comment/create"
				, data:{"postId":postId, "content":content}
				
				, success:function(data) {
					if (data.code == 1) {
						alert("댓글이 게시되었습니다");
						location.reload();
					} else {
						alert(data.errorMessage);
					}
				}
				, error:function(jqXHR, textStatus, errorThrown) {
					let errorMsg = jqXHR.responseJSON.status;
					alert(errorMsg + ":" + textStatus);
				}
			});
		});
	});
</script>