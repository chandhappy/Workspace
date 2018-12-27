import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class NC {
	static BufferedWriter bw;
	static StringBuffer oneLine1;
	static String CSV_SEPARATOR;
	static FileOutputStream fileOut;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File currentDir = new File(
				"C:\\Users\\INCBASHA\\Desktop\\reliance\\Auto DR 4 Arindam");
		// fileOut = new FileOutputStream(currentDir+"Patientbook.xls");
		CSV_SEPARATOR = ",";
		// current directory
		bw = new BufferedWriter(new FileWriter(
				"C:\\Users\\INCBASHA\\Desktop\\Patientbook.csv"));
		oneLine1 = new StringBuffer();

		oneLine1.append("UID");
		oneLine1.append(CSV_SEPARATOR);
		oneLine1.append("Gender");
		oneLine1.append(CSV_SEPARATOR);
		oneLine1.append("Age");
		oneLine1.append(CSV_SEPARATOR);
		oneLine1.append("Doctor");
		oneLine1.append(CSV_SEPARATOR);
		oneLine1.append("VisitDate");
		oneLine1.append(CSV_SEPARATOR);
		oneLine1.append("Advice");
		oneLine1.append(CSV_SEPARATOR);
		oneLine1.append("OD DR");
		oneLine1.append(CSV_SEPARATOR);
		oneLine1.append("OD AMD");
		oneLine1.append(CSV_SEPARATOR);
		oneLine1.append("OD ONH");
		oneLine1.append(CSV_SEPARATOR);
		oneLine1.append("OD HR");
		oneLine1.append(CSV_SEPARATOR);
		oneLine1.append("OS DR");
		oneLine1.append(CSV_SEPARATOR);
		oneLine1.append("OS AMD");
		oneLine1.append(CSV_SEPARATOR);
		oneLine1.append("OS ONH");
		oneLine1.append(CSV_SEPARATOR);
		oneLine1.append("OS HR");
		bw.write(oneLine1.toString());
		bw.newLine();
		displayDirectoryContents(currentDir);
		bw.close();
	}

	public static void displayDirectoryContents(File dir) {
		try {

			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					displayDirectoryContents(file);
				} else {

					System.out.println("     file:" + file.getCanonicalPath());
					try (PDDocument pd = PDDocument
							.load(new File(file.getCanonicalPath()))) {
						pd.getClass();
						if (!pd.isEncrypted()) {
							PDFTextStripperByArea stripper = new PDFTextStripperByArea();
							stripper.setSortByPosition(true);
							PDFTextStripper tStripper = new PDFTextStripper();
							String pdfFileInText = tStripper.getText(pd);
							// System.out.println("Text:" + st);
							StringBuffer oneLine = new StringBuffer();
							// split by whitespace
							String lines[] = pdfFileInText.split("\\r?\\n");
							int linecount = 0;
							int count1 = 0, count2 = 0, count3 = 0, count4 = 0;
							// int rowCount=1;
							int row1 = 0;

							for (String line : lines) {
								linecount++;
								if (line.contains("UID:")) {
									// System.out.println(line);
									// row1= sheet.createRow(rowCount);
									// row1.createCell(0).setCellValue(line.split(":
									// ")[1]);
									// System.out.println(line.split(": "));
									oneLine.append(line.split(": ")[1]);
									System.out.println(
											line.split(": ")[1].toString());
									oneLine.append(CSV_SEPARATOR);
								}

								if (line.contains("Gender:")) {
									// System.out.println(line);
									oneLine.append(line.split(": ")[1]);
									oneLine.append(CSV_SEPARATOR);
								}

								if (line.contains("Age:")) {
									// System.out.println(line);
									oneLine.append(line.split(": ")[1]);
									oneLine.append(CSV_SEPARATOR);
								}

								if (line.contains("Visit Date:")) {
									// System.out.println(line);
									oneLine.append(line.split(": ")[1]
											.replaceAll(",", ""));
									oneLine.append(CSV_SEPARATOR);
								}

								if (line.contains("DR:")) {
									System.out.println(line);
									System.out.println(lines[linecount]);
									if (count1 == 0) {
										oneLine.append(lines[linecount]);
										oneLine.append(CSV_SEPARATOR);
									} else if (count1 == 1) {
										oneLine.append(lines[linecount]);
										oneLine.append(CSV_SEPARATOR);
									}
									count1++;
								}
								
								if (line.contains("AMD:")) {
									System.out.println(line);
									System.out.println(lines[linecount]);
									if (count2 == 0) {
										oneLine.append(lines[linecount]);
										oneLine.append(CSV_SEPARATOR);
									} else if (count2 == 1) {
										oneLine.append(lines[linecount]);
										oneLine.append(CSV_SEPARATOR);
									}
									count2++;
								}
								
								if (line.contains("ONH region:")) {
									System.out.println(line);
									System.out.println(lines[linecount]);
									if (count3 == 0) {
										oneLine.append(lines[linecount]);
										oneLine.append(CSV_SEPARATOR);
									} else if (count3 == 1) {
										oneLine.append(lines[linecount]);
										oneLine.append(CSV_SEPARATOR);
									}
									count3++;
								}
								if (line.contains(
										"Hypertensive Retinopathy:")) {
									System.out.println(line);
									System.out.println(lines[linecount]);
									if (count4 == 0) {
										oneLine.append(lines[linecount]);
										oneLine.append(CSV_SEPARATOR);
									} else if (count4 == 1) {
										oneLine.append(lines[linecount]);
										oneLine.append(CSV_SEPARATOR);
									}
									count4++;
								}
								
								if (line.contains("Advice:") & row1 == 0) {
									System.out.println(line);
									oneLine.append(lines[linecount]);
									oneLine.append(CSV_SEPARATOR);
									row1++;
									// rowCount++;
									count1 = count2 = count3 = count4 = 0;
								}	
								
								if (line.contains("Exam Center:")) {
									// System.out.println(line);
									oneLine.append(line.split(": ")[1]);
									oneLine.append(CSV_SEPARATOR);
								}
								
								System.out.println(line);
							}
							bw.write(oneLine.toString());
							bw.newLine();
							bw.flush();
						}
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
