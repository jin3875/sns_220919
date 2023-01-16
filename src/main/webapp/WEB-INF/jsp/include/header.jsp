<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="header bg-info d-flex justify-content-between">
	<%-- logo --%>
	<div class="logo d-flex align-items-center">
		<h1 class="text-white ml-3"><a href="/timeline/timeline_view" class="text-white">Marondalgram</a></h1>
	</div>
	
	<%-- 로그인 정보 --%>
	<c:if test="${not empty userId}">
		<div class="login-info d-flex align-items-end mb-3 mr-5">
			<span class="text-white">${userName}님 안녕하세요</span>
			<a href="/user/sign_out" class="ml-2 text-white font-weight-bold">로그아웃</a>
		</div>
	</c:if>
</div>