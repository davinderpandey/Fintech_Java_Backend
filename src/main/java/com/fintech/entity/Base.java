package com.fintech.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Base Entity for sub entities.
 * 
 * @author Davinder Pandey
 * @since 18-AUG-2023
 */
public class Base implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5979211121019117229L;

	@Column(name = "CREATED_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "MODIFIED_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "CREATED_BY", nullable = true, foreignKey = @ForeignKey(name = "FK_APPLICATION_MODULE_ID_USER_C"))
	private User createdBy;

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "MODIFIED_BY", nullable = true, foreignKey = @ForeignKey(name = "FK_APPLICATION_MODULE_ID_USER_M"))
	private User modifiedBy;

	@Column(name = "ENABLED_FLAG")
	private boolean enabledFlag;

}