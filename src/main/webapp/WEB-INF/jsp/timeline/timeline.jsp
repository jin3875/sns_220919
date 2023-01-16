<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="post-box border mt-4">
	<textarea class="form-control border-0" rows="5"></textarea>
	<div class="d-flex justify-content-between">
		<img src="https://cdn.pixabay.com/photo/2015/12/22/04/00/photo-1103595_960_720.png" alt="사진 아이콘" width="40">
		<button type="submit" class="btn btn-info">게시</button>
	</div>
</div>

<div class="timeline-box border mt-4">
	<div class="d-flex justify-content-between">
		<span><b>${userLoginId}</b></span>
		<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" alt="점 3개 아이콘" width="30">
	</div>
	<img src="https://cdn.pixabay.com/photo/2023/01/10/09/51/sand-dunes-7709400_960_720.jpg" width="500">
	<div class="mt-3">
		<img src="https://www.iconninja.com/files/214/518/441/heart-icon.png" alt="비어진 하트" width="20">
		<span>좋아요</span>
	</div>
	<div class="mt-3">
		<span><b>${userLoginId}</b></span>
		<span>....</span>
	</div>
	<div class="mt-3">
		<span>댓글</span>
		<div class="border-top border-bottom pt-3 pb-3">
			<span><b>aaa</b></span>
			<span>:</span>
			<span>.....</span>
			<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" alt="삭제 버튼 아이콘" width="10">
		</div>
	</div>
	<div class="d-flex justify-content-between">
		<input type="text" class="border-0" placeholder="댓글 달기">
		<button type="submit" class="btn btn-light">댓글 게시</button>
	</div>
</div>