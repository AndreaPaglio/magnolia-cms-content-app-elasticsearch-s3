/*
 *
 */
package com.whitelabel.app.generic.custom.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.whitelabel.app.generic.annotation.Format;
import com.whitelabel.app.generic.annotation.GenericEntity;
import com.whitelabel.app.generic.entity.GenericItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * * It's a item must extends {@link GenericItem}
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@GenericEntity(name = "test", fieldId = "id")
public class Log extends GenericItem {

	/** The id. */
	private String id;

	/** The level. */
	private String level;

	/** The logger name. */
	private String loggerName;

	/** The hostname. */
	private String hostname;

	/** The message. */
	private String message;

	/** The thread. */
	private String thread;

	/** The time millis. */
	@Format(formatter = "EE MMM dd HH:mm:ss zzzz yyyy")
	private Date timeMillis;
}
