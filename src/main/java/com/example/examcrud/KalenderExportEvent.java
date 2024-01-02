package com.example.examcrud;

import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class KalenderExportEvent {
	private String zusammenfassung;
	private Date startDatum;
//    private Date endDatum;

	//    public KalenderExportEvent(String zusammenfassung, Date startDatum, Date endDatum) {
	public KalenderExportEvent(String zusammenfassung, Date startDatum) {
		this.zusammenfassung = zusammenfassung;
		this.startDatum = startDatum;
//        this.endDatum = endDatum;
	}

	public String zuVCalendarString() {

		VEvent event = new VEvent(
				new DateTime(startDatum),
//                new DateTime(endDatum),
				zusammenfassung
		);

		SimpleDateFormat datumFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
		String vCalendarString =
				"BEGINN:VEVENT\n" +
//                        "VERSION:2.0\n" +
						"SUMMARY:" + zusammenfassung + "\n" +
						"DTSTART:" + datumFormat.format(startDatum) + "\n" +
//                        "DTEND:" + datumFormat.format(endDatum) + "\n" +
						"END:VEVENT\n";
		return vCalendarString;
	}
}
