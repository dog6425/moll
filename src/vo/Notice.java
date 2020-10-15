package vo;

public class Notice {
	// Ä¸½¶È­
	private int noticeId;
	private String noticeTitle;
	private String noticeContent;
	private String noticeDate;
	
	// ÇÊµåº°(¸â¹öº¯¼ö) °ÙÅÍ/¼ÂÅÍ »ý¼º
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public String getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}	
}