package ee509_assign1;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;


public class WriteFile {
	
	//Based on: http://www.homeandlearn.co.uk/java/write_to_textfile.html
	private String path;
	private boolean append_to_file = false;
	
	public WriteFile()
	{
		setPath("NULL");
	}
	
	public WriteFile(String file_path)
	{
		setPath(file_path);
	}
	
	public WriteFile(String file_path, boolean append_value)
	{
		setPath(file_path);
		setAppend_to_file(append_value);		
	}
	
	public void writeToFile(String textLine, String file_path, boolean append_value ) throws IOException
	{
		this.setPath(file_path);
		this.setAppend_to_file(append_value);
		
		FileWriter write = new FileWriter(getPath(), isAppend_to_file());
		PrintWriter print_line = new PrintWriter(write);
		
		print_line.printf("%s" + "%n", textLine);
		print_line.close();
		
	}
	
	public void writeToFile(String textLine) throws IOException
	{
		FileWriter write = new FileWriter(getPath(), isAppend_to_file());
		PrintWriter print_line = new PrintWriter(write);
		
		print_line.printf("%s" + "%n", textLine);
		print_line.close();
		
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isAppend_to_file() {
		return append_to_file;
	}

	public void setAppend_to_file(boolean append_to_file) {
		this.append_to_file = append_to_file;
	}
	

}
