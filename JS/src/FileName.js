//List to store each Excel row in
var excelData = new Packages.java.util.ArrayList();

//Open an Excel file
var excelFile = new Packages.java.io.File("C:/Users/your_user_name/Documents/name_of_file.xls");
var excel = Packages.jxl.Workbook.getWorkbook(excelFile);

//Read in the first sheet
var sheet = excel.getSheet(0);

//Variables for the current row and column
var myRow = 1;
var myCol = 0;

//Variable for the last row or last column
var rowStop = 0;
var colStop = 0;

while (rowStop == 0) {
    var msgString = '<result>';
    while (colStop == 0) {
        //See if this column is valid
        //If so, write the cell
        try {
            //logger.error(sheet.getCell(myCol, myRow).getContents());
            msgString = msgString + '<col' + myCol + '>' + sheet.getCell(myCol, myRow).getContents() + '</col' + myCol + '>';
        }
        //If not, stop processing at this column and move on to the next row.
        catch(ex) {
            colStop = 1;    
        }
        myCol++;
    }
    myRow++;
    myCol = 0;
    colStop = 0;

    //See if the next row is valid
    try {
        sheet.getCell(myCol, myRow);
    }
    //If not, stop processing the Excel file.
    catch(ex) {
        rowStop = 1;    
    }

    msgString = msgString + '</result>';

    //Clear out invalid XML characters
    msgString = msgString.replace(/&/g, '&amp;');
    msgString = msgString.replace(/\'/g, '&apos;');
    msgString = msgString.replace(/\"/g, '&quot;');

    //Add the message to the message list
    var msgXML = new XML(msgString.toString());
    excelData.add(msgXML.toString());
}

//Close the Excel file
excel.close()

return excelData;