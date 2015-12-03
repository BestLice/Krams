package PictureTool;
/*
 * Einstellung einzelne Typen ausblenden
 * Log erstellen
 * Speicherung letzter Einstellungen
 */

public class MyLog {
	private static MyLog singleInstance =  new MyLog();
 
	private MyLogDlg dlg = null;
	private MyTextArea area = null;
	
	private MyLog(){
		dlg = new MyLogDlg();
		this.area = dlg.area;
		area.setText("KICK IT !!!");
	}
	
	public void appendFreeLine(int anzahl){
		for(int i = 0; i < anzahl; i++){
			area.setText( area.getText()+"\n");
		}
	}
	public void appendInfo(String str){
		if(dlg.showInfo()){
			str = "INFO : "+str;
			area.setText( area.getText().equalsIgnoreCase("") ? str : area.getText()+"\n"+str);
		}
	}
	public void appendDebug(String str){
		if(dlg.showDebug()){
			str = "DEBUG : "+str;
			area.setText( area.getText().equalsIgnoreCase("") ? str : area.getText()+"\n"+str);
		}
	}
	public void appendError(String str){
		if(dlg.showError()){
			str = "ERROR : "+str;
			area.setText( area.getText().equalsIgnoreCase("") ? str : area.getText()+"\n"+str);
		}
	}
	public void appendInfo(int zahl){
		if(dlg.showInfo()){
			String str = "INFO INTEGER : "+zahl;
			area.setText( area.getText().equalsIgnoreCase("") ? str : area.getText()+"\n"+str);			
		}
	}
	public void appendDebug(int zahl){
		if(dlg.showDebug()){
			String str = "DEBUG INTEGER : "+zahl;
			area.setText( area.getText().equalsIgnoreCase("") ? str : area.getText()+"\n"+str);
		}
	}
	public void appendError(int zahl){
		if(dlg.showError()){
			String str = "ERROR INTEGER : "+zahl;
			area.setText( area.getText().equalsIgnoreCase("") ? str : area.getText()+"\n"+str);
		}
	}
	public void appendLine(){
		if(dlg.showLine()){
			String str = "------------------------------------";
			area.setText( area.getText().equalsIgnoreCase("") ? str : area.getText()+"\n"+str);
		}
	}
	public void appendDblLine(){
		if(dlg.showLine()){
			String str = "====================================";
			area.setText( area.getText().equalsIgnoreCase("") ? str : area.getText()+"\n"+str);
		}
	}
	
	public void setVisible(boolean val){
		dlg.setVisible(val);
	}
	
    public static MyLog getInstance() {
    	 if(null == singleInstance) {
             singleInstance = new MyLog();
         }
        return singleInstance;
    }
}
