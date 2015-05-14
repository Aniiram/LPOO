package maze.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

public class TrabalhoFicheiro {
	
	static void carregarJogo(labirinto.Labirinto jogo) {
		JFileChooser fileChooser = new JFileChooser("C:/Users/alcamilo/workspace/lpoo/Labirinto/ficheiros");
		
		fileChooser.setDialogTitle("Abrir Jogo");
		fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
		fileChooser.setApproveButtonText("Abrir");
		
		int returnVal = fileChooser.showOpenDialog(fileChooser);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				Control.jogo = (labirinto.Labirinto) deserialize(fileChooser.getSelectedFile());
				Control.showPanel("gamePanel", 500, 604);
			} catch (ClassNotFoundException | IOException e) {
				System.exit(0);
				e.printStackTrace();
			}
			}
	}
	
	static void salvarJogo(labirinto.Labirinto jogo) throws IOException {
		JFileChooser fileChooser = new JFileChooser("C:/Users/alcamilo/workspace/lpoo/Labirinto/ficheiros");
		
		fileChooser.setDialogTitle("Salvar Jogo");
		fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
		fileChooser.setApproveButtonText("Salvar");
		
		int returnVal = fileChooser.showSaveDialog(fileChooser);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			serialize(jogo, fileChooser.getSelectedFile());
		}
	}
	
	public static Object deserialize(File fileName) throws IOException,
	ClassNotFoundException {
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object obj = ois.readObject();
		ois.close();
		return obj;
	}

	// serialize the given object and save it to file
	public static void serialize(Object obj, File fileName)
			throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);

		fos.close();
	}

}
