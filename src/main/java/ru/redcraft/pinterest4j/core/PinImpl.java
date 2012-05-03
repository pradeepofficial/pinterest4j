package ru.redcraft.pinterest4j.core;

import ru.redcraft.pinterest4j.Board;
import ru.redcraft.pinterest4j.Pin;

public class PinImpl implements Pin {

	private final long id;
	private final String description;
	private final double price;
	private final String link;
	private final String imageURL;
	private final Board board;
	
	public PinImpl(long id, String description, double price, String link, String imageUrl,
			Board board) {
		super();
		this.id = id;
		this.description = description;
		this.price = price;
		this.link = link;
		this.imageURL = imageUrl;
		this.board = board;
	}

	public long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
	
	public double getPrice() {
		return price;
	}

	public String getLink() {
		return link;
	}

	public String getImageURL() {
		return imageURL;
	}

	public Board getBoard() {
		return board;
	}

}