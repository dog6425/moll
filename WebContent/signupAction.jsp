<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.* %>
<%@ page import = "vo.*" %>
<%
	if(session.getAttribute("loginMemberEmail") == null) {
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		return;
	}
	request.setCharacterEncoding("utf-8");
	String memberEmail = request.getParameter("memberEmail");
	String memberPw = request.getParameter("memberPw");
	String memeberName = request.getParameter("memberName");
	
	MemberDao memberDao = new MemberDao();
	Member member = memberDao.selectMemberEmailCk("memberEmail");
	if(member != null) {
		System.out.print("이미 사용중인 메일 입니다.");
		response.sendRedirect(request.getContextPath()+"/member/signup.jsp");
		return ;
	}
	Member paramMember = new Member();
	paramMember.getMemberEmail();
	paramMember.getMemberPw();
	paramMember.getMemberName();
	memberDao.insertMember(paramMember); // 회원가입 메서드 호출
	
	response.sendRedirect(request.getContextPath()+"/member/login.jsp");
%>