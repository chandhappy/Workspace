try{
	var len= msg.*::['worklist_entry'].*::['patient_Bith_date'].toString().lenth();
	if(len==14){
		var PatientAGE =(DateUtil.convertDate("yyyyMMddhhmmss", "yyyy",DateUtil.getCurrentDate("yyyyMMddhhmmss"))-
				(DateUtil.convertDate("yyyyMMddhhmmss", "yyyy",msg.*::['worklist_entry'].*::['patient_Bith_date'].toString())));
		channelMap.put('PatientAGE', validate(PatientAGE, '', new Array()));
	}else if(len==8){
		var PatientAGE =(DateUtil.convertDate("yyyyMMddhhmmss", "yyyy",DateUtil.getCurrentDate("yyyyMMddhhmmss"))-
				(DateUtil.convertDate("yyyyMMdd", "yyyy",msg.*::['worklist_entry'].*::['patient_Bith_date'].toString())));
		channelMap.put('PatientAGE', validate(PatientAGE, '', new Array()));
	}else if(len < 8 || len > 2 ||len < 2 ||len == 0 ){
		logger.error(e);
	}else if(len==2){
		var PatientAGE = msg.*::['worklist_entry'].*::['patient_Bith_date'].toString();
		channelMap.put('PatientAGE', validate(PatientAGE, '', new Array()));	
	}
}
catch (e) {
	logger.error(e);
	PatientAGE ='';
}