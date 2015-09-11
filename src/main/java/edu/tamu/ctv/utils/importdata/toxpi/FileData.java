package edu.tamu.ctv.utils.importdata.toxpi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class FileData {
    private String fileName;
    private List<Component> components;
    private List<Substance> chemicals;
    private Map<String, Set<Component>> typeComponentsMap;
    private Map<String, Set<Component>> sourceComponentsMap;
    private String originalFileName;

    public FileData(String fileName, List<Substance> chemicals, List<Component> components,Map<String, Set<Component>> typeComponentsMap, Map<String, Set<Component>> sourceComponentsMap, String originalFileName) {
        this.fileName = fileName;
        this.chemicals = chemicals;
        this.components = components;
        this.typeComponentsMap = typeComponentsMap;
        this.sourceComponentsMap = sourceComponentsMap;
        this.originalFileName = originalFileName;
    }
    
    public List<Component> getComponents() {
        return components;
    }

    public void setComponentNames(List<Component> components) {
        this.components = components;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Substance> getChemicals() {
        return chemicals;
    }

    public void setChemicals(List<Substance> chemicals) {
        this.chemicals = chemicals;
    }
    
    public List<String> getComponentNames(){
        List<String> res = new ArrayList<String>();
        for(Component c:this.components){
            res.add(c.getName());
        }
        return res;
    }

    public Map<String, Set<Component>> getSourceComponentsMap() {
        return sourceComponentsMap;
    }

    public void setSourceComponentsMap(Map<String, Set<Component>> sourceComponentsMap) {
        this.sourceComponentsMap = sourceComponentsMap;
    }

    public Map<String, Set<Component>> getTypeComponentsMap() {
        return typeComponentsMap;
    }

    public void setTypeComponentsMap(Map<String, Set<Component>> typeComponentsMap) {
        this.typeComponentsMap = typeComponentsMap;
    }
    
    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }
}
