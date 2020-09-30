package com.gof.springcloud.elasticsearch;

import java.io.IOException;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MappingBuilder {
	private final static Logger log = LoggerFactory.getLogger(ElasticServiceImpl.class);

	public static XContentBuilder mappingBuild() {
		try {
			XContentBuilder builder = XContentFactory.jsonBuilder();
			builder.startObject();
			{
				builder.startObject("properties");
				{
					builder.startObject("id");
					builder.field("type", "keyword");
					builder.endObject();

					builder.startObject("name");
					builder.field("type", "keyword");
					builder.endObject();

					builder.startObject("title");
					builder.field("type", "text");
					builder.endObject();

					builder.startObject("totalCost");
					builder.field("type", "double");
					builder.endObject();

					// days - nested
					builder.startObject("days");
					{
						builder.field("type", "nested");
						builder.startObject("properties");
						{
							builder.startObject("date");
							builder.field("type", "date");
							builder.field("format", "yyyy-MM-dd HH:mm");
							builder.endObject();

							// nodes - nested
							builder.startObject("nodes");
							{
								builder.field("type", "nested");
								builder.startObject("properties");
								{
									builder.startObject("type");
									builder.field("type", "keyword");
									builder.endObject();
									// activity
									builder.startObject("category");
									builder.field("type", "keyword");
									builder.endObject();

									builder.startObject("cost");
									builder.field("type", "double");
									builder.endObject();

									builder.startObject("rating");
									builder.field("type", "integer");
									builder.endObject();

									builder.startObject("review");
									builder.field("type", "text");
									builder.endObject();

									builder.startObject("location");
									builder.field("type", "keyword");
									builder.endObject();

									builder.startObject("timeStart");
									builder.field("type", "date");
									builder.field("format", "yyyy-MM-dd HH:mm");
									builder.endObject();

									builder.startObject("timeEnd");
									builder.field("type", "date");
									builder.field("format", "yyyy-MM-dd HH:mm");
									builder.endObject();

									// leg
									builder.startObject("from");
									builder.field("type", "keyword");
									builder.endObject();

									builder.startObject("to");
									builder.field("type", "keyword");
									builder.endObject();

									builder.startObject("startOn");
									builder.field("type", "date");
									builder.field("format", "yyyy-MM-dd HH:mm");
									builder.endObject();

									builder.startObject("returnDate");
									builder.field("type", "date");
									builder.field("format", "yyyy-MM-dd HH:mm");
									builder.endObject();

									builder.startObject("transportMode");
									builder.field("type", "keyword");
									builder.endObject();
								}
								builder.endObject();
							}
							builder.endObject();
						}
						builder.endObject();
					}
					builder.endObject();
				}
				builder.endObject();
			}
			builder.endObject();
			return builder;
		} catch (IOException e) {
			log.error("mapping builder error:{}", e);
		}
		return null;
	}
}
