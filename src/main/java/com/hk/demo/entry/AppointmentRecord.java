package com.hk.demo.entry;

import java.io.Serializable;

public class AppointmentRecord implements Serializable{

	private static final long serialVersionUID = 4481101776067655418L;
	private String appointmentRecordId;
	private String schedulingDetailId;

	private String hospitalId;

	public String getAppointmentRecordId() {
		return appointmentRecordId;
	}

	public void setAppointmentRecordId(String appointmentRecordId) {
		this.appointmentRecordId = appointmentRecordId;
	}

	public String getSchedulingDetailId() {
		return schedulingDetailId;
	}

	public void setSchedulingDetailId(String schedulingDetailId) {
		this.schedulingDetailId = schedulingDetailId;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	@Override
	public String toString() {
		return "AppointmentRecord [appointmentRecordId=" + appointmentRecordId + ", schedulingDetailId="
				+ schedulingDetailId + ", hospitalId=" + hospitalId + "]";
	}
}
