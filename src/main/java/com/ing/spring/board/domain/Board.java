package com.ing.spring.board.domain;

import java.util.Date;

public class Board {
	
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String boardFilename;
	private String boardFilerename;
	private String boardFilepath;
	private long boardFilelength;
	private int boardCount;
	private Date createDate;
	private Date updateDate;
	private char boardStatus;
	
	
	
	public long getBoardFilelength() {
		return boardFilelength;
	}
	public void setBoardFilelength(long boardFilelength) {
		this.boardFilelength = boardFilelength;
	}
	public int getBoardNo() {
		
		
		return boardNo;
		
		
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public String getBoardFilename() {
		return boardFilename;
	}
	public void setBoardFilename(String boardFilename) {
		this.boardFilename = boardFilename;
	}
	public String getBoardFilerename() {
		return boardFilerename;
	}
	public void setBoardFilerename(String boardFilerename) {
		this.boardFilerename = boardFilerename;
	}
	public String getBoardFilepath() {
		return boardFilepath;
	}
	public void setBoardFilepath(String boardFilepath) {
		this.boardFilepath = boardFilepath;
	}
	public int getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public char getBoardStatus() {
		return boardStatus;
	}
	public void setBoardStatus(char boardStatus) {
		this.boardStatus = boardStatus;
	}
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardWriter=" + boardWriter + ", boardFilename=" + boardFilename + ", boardFilerename="
				+ boardFilerename + ", boardFilepath=" + boardFilepath + ", boardCount=" + boardCount + ", createDate="
				+ createDate + ", updateDate=" + updateDate + ", boardStatus=" + boardStatus + "]";
	}
	
	
	

}
