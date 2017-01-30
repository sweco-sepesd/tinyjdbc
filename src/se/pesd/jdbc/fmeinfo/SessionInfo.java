package se.pesd.jdbc.fmeinfo;

import COM.safe.fmeobjects.FMEException;
import COM.safe.fmeobjects.IFMESession;
import se.pesd.jdbc.annotations.Column;
import se.pesd.jdbc.annotations.Table;

@Table(catalog = "", schema = "fme", tableName = "session")
public class SessionInfo {
	
	final IFMESession session;

	@Column(name = "build", ordinalPosition = 1)
	public int buildNumber() {
		return session.fmeBuildNumber();
	}
	@Column(name = "home", ordinalPosition = 2)
	public String fmeHome() {
		return session.fmeHome();
	}
	@Column(name = "version", ordinalPosition = 3)
	public String fmeVersion() {
		return session.fmeVersion();
	}
	@Column(name = "license_type", ordinalPosition = 4)
	public String licenseType() {
		try {
			return session.getLicenseManager().getLicenseType();
		} catch (FMEException e) {
			return "error";
		}
	}

	@Column(name = "max_fme_processes", ordinalPosition = 5)
	public int maxFMEProcesses() {
			return session.getLicenseManager().getMaxFMEProcesses();
	}
	
	@Column(name = "max_worker_processes", ordinalPosition = 6)
	public int maxWorkerProcesses() {
			return session.getLicenseManager().getMaxFMEWorkerProcesses();
	}
	@Column(name = "logfile", ordinalPosition = 7)
	public String logFile() {
		return session.logFile().getFileName();
	}
		
	public SessionInfo(IFMESession session) {
		this.session = session;
		
	} 
	
	

}
