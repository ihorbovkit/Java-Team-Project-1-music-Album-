package com.eleks.abums.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.eleks.albums.AlbumInfo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class AlbumParser {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws JsonParseException,
			JsonMappingException, IOException {
		InputStream input = new FileInputStream("src/album1.json");
		ObjectMapper maper = new ObjectMapper();
		TypeFactory typeFactory = TypeFactory.defaultInstance();
		List<AlbumInfo> alb = maper.readValue(input, typeFactory
				.constructCollectionType(ArrayList.class, AlbumInfo.class));

		/* SORTING List */

		Collections.sort(alb, new Comparator() {
			@Override
			public int compare(Object softDrinkOne, Object softDrinkTwo) {
				// use instanceof to verify the references are indeed of the
				// type in question
				if (((AlbumInfo) softDrinkOne).getArtist().equals(((AlbumInfo) softDrinkTwo).getArtist())) {
					if (((AlbumInfo) softDrinkOne).getTitle().equals(((AlbumInfo) softDrinkTwo).getTitle()) && ((AlbumInfo) softDrinkOne).getArtist().equals(
									((AlbumInfo) softDrinkTwo).getArtist())) {
						return (new Double(((AlbumInfo) softDrinkOne)
								.getDuration()))
								.compareTo(((AlbumInfo) softDrinkTwo)
										.getDuration());
					}
					/*It's not used for Quality*/
					
					/*if (((AlbumInfo) softDrinkOne).getDuration() == ((AlbumInfo) softDrinkTwo).getDuration()){
						return ((AlbumInfo) softDrinkOne).getQuality().getGenre().compareTo(
								((AlbumInfo) softDrinkTwo).getQuality().getGenre());
					}*/

					return ((AlbumInfo) softDrinkOne).getTitle().compareTo(
							((AlbumInfo) softDrinkTwo).getTitle());
				} else {
					return ((AlbumInfo) softDrinkOne).getArtist().compareTo(
							((AlbumInfo) softDrinkTwo).getArtist());
				}
			}
		});

		/* Printing JSON file */
		for (int i = 0; i < alb.size(); i++) {
			if (alb.get(i).getQuality() == null) {
				System.out.println(alb.get(i).getArtist() + ", "
						+ alb.get(i).getTitle() + ", "
						+ alb.get(i).getDuration() + "; ");
			} else {
				System.out.println(alb.get(i).getArtist() + ", "
						+ alb.get(i).getTitle() + ", "
						+ alb.get(i).getDuration() + ", "
						+ alb.get(i).getQuality().getFormat() + ", "
						+ alb.get(i).getQuality().getGenre() + "; ");
			}
		}
	}

}
