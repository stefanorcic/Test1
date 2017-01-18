package geometrija;

import java.awt.Color;
import java.awt.Graphics;

public abstract class PovrsinskiOblik extends Oblik {
	
	private String bojaUnutraString = "bela";
	private Color bojaUnutra = Color.WHITE;
	
	
	public abstract void popuni(Graphics g);

	public Color getBojaUnutra() {
		return bojaUnutra;
	}

	public void setBojaUnutra(Color bojaUnutra) {
		this.bojaUnutra = bojaUnutra;
	}
	
	public String getBojaUnutraString() {
		return bojaUnutraString;
	}

	public void setBojaUnutraString(String bojaUnutraString) {
		this.bojaUnutraString = bojaUnutraString;
	}
	
	public static boolean proveraUnosa(int vrednost)throws Exception{
		if(vrednost > 0)
			return true;
		else
			throw new Exception();
			
		
	}
	
}
