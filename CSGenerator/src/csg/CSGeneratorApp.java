/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg;

import csg.data.CSGData;
import csg.file.CSGFiles;
import csg.style.CSGStyle;
import csg.workspace.CSGWorkspace;
import djf.AppTemplate;
import java.util.Locale;
import static javafx.application.Application.launch;

/**
 *
 * @author Jackie Quach
 */
public class CSGeneratorApp extends AppTemplate {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	Locale.setDefault(Locale.US);
	launch(args);
    }

    @Override
    public void buildAppComponentsHook() {
        dataComponent = new CSGData(this);
        workspaceComponent = new CSGWorkspace(this);
        fileComponent = new CSGFiles(this);
        styleComponent = new CSGStyle(this);
    }
    
    public void buildAppComponentsHookTest() {
        dataComponent = new CSGData(this);
        fileComponent = new CSGFiles(this);
    }
    
}
