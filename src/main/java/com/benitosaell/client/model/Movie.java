package com.benitosaell.client.model;

import java.util.Date;
import java.util.List;

public class Movie {
	private int id;
	private String title;
	private String synopsis;
	private String poster = "image.jpg";
	private String review;
	private Date releaseDate = new Date();
	private List<Comment> comments;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", synopsis=" + synopsis + ", poster=" + poster + ", review="
				+ review + ", releaseDate=" + releaseDate + "]";
	}

}
