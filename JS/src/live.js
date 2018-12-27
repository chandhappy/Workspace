var ExcelData = new Packages.java.util.ArrayList();
var SourceFile = new Packages.java.io.File("C:/My Data/ReadCSV/test.xls");
var WorkBook = new Packages.jxl.Workbook.getWorkbook(SourceFile);
var Sheet = WorkBook.getSheet(0);
var RowIndex;
var ColoumnIndex;
var MessageString = '<MWL>';
var RowEnd = 0;
var ColoumnEnd = 0;
var Rowlength = 5;
var Coloumnlength = 5;

for (RowIndex = 1; RowIndex <= Rowlength; RowIndex++) {
	logger.info(RowIndex.toString);
	for (ColoumnIndex = 0; ColoumnIndex <= Coloumnlength; ColoumnIndex++) {
		//logger.info(MessageString);
		MessageString = MessageString + '<Col' + ColoumnIndex + '>'
				+ Sheet.getCell(ColoumnIndex, RowIndex).getContents() + '</Col'
				+ ColoumnIndex + '>';
		//logger.info(MessageString);
	}
	MessageString = MessageString + '</MWL>';
	//logger.info(MessageString);
}

/*while(RowEnd == 0){
 while(ColoumnEnd == 0){
 try {
 MessageString = MessageString + '<Col'+ColoumnIndex+'>'+Sheet.getCell(ColoumnIndex,RowIndex).getContents()+ '</Col'+ColoumnIndex+'>';
 //logger.info(MessageString);
 }
 catch(ex){
 ColoumnEnd = 1;
 }
 ColoumnIndex++;
 }
 RowIndex++;
 RowEnd		= 0;
 ColoumnEnd	= 0;
 try{
 sheet.getCell(ColoumnIndex,RowIndex);
 }
 catch(ex) {
 rowStop = 1;    
 }
 //logger.info(MessageString);
 MessageString = MessageString + '</MWL>';*/

logger.info(MessageString);
//logger.info(Packages.jxl.Workbook.getFirstSheetIndex().toString());
MessageString = MessageString.replace(/&/g, '&amp;');
MessageString = MessageString.replace(/\'/g, '&apos;');
MessageString = MessageString.replace(/\"/g, '&quot;');
//logger.info(MessageString);

var TransformedXML = new XML(MessageString.toString());
ExcelData.add(TransformedXML.toString());
//logger.info(TransformedXML.toString());
//}

//Close the Excel file
WorkBook.close();
//logger.info(ExcelData);
return ExcelData;