package edu.tamu.ctv.utils.importdata.toxpi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ToxPiConfig {
	public static final int SMALL_IMAGE_WIDTH = 100;
	public static final int SMALL_IMAGE_HEIGHT = 100;
	public static final int BIG_IMAGE_WIDTH = 500;
	public static final int BIG_IMAGE_HEIGHT = 500;
        public static final int CHART_AS_IMAGE_HEIGHT = 768;
        public static final int CHART_AS_IMAGE_WIDTH = 1024;
        public static final int DRAG_SPEED_X = 10;
        public static final int DRAG_SPEED_Y = 10;
        public static final int MAX_ZOOM_LEVEL = 12;
        public static String CURRENT_DIR;
        public static int ADJUSTED_SMALL_IMAGE_HEIGHT = 100;
        public static boolean DISPLAY_MISSING_DATA = true;
	public static boolean DISPLAY_95CI = true;
        
	//type list
	public static final String ASSAY = "assay";
	public static final String PATHWAY = "pathway";
	public static final String CHEMPROP = "chemprop";
	public static final String EXPOSURE = "exposure";
	public static final String TOXREFDB = "toxrefdb";
	public static final String CUSTOM = "custom";
        public static final boolean DEBUG = false;
        public static boolean IS_CANCELED = false;
        //indicator wheather user changed slices content or slices nummber, if so we need to re-read data to be able to use RECREATE functions
        public static boolean WAS_DATA_CHANGED;
        //indicator wheather we should skip bootstrapping step when there is only one component per slice for each slice.
        public static boolean SKIP_BOOTSTRAPPING = false;
        
    
        public static enum IMAGE{SIMPLE,SIMPLE_WITH_NAME,COMPLETE,PREVIEW};
        //scaling list
	public enum SCALING{
            LOG10("-log10(x)+6"),MAX("-log10(x)+log10(max(x))"),HITCOUNT("hit count"),LN("-ln(x)+ln(max(x))"),SQRT("sqrt(x)"),LINEAR("linear(x)");

            public static SCALING getType(Object selectedItem) {
                if(selectedItem instanceof String){
                    String s = (String)selectedItem;
                    for(SCALING scal: SCALING.values()){
                        if(s.equals(scal.toString())) return scal;
                    }
                }
                return LINEAR;
            }
            
            String name;
            
            SCALING(String name){
                this.name = name;
            }
                        
          @Override
            public String toString(){
                return this.name;
            }
            
        }
	
	public static final String[] TYPES = {ToxPiConfig.ASSAY, ToxPiConfig.PATHWAY, ToxPiConfig.CHEMPROP, ToxPiConfig.EXPOSURE, ToxPiConfig.TOXREFDB, ToxPiConfig.CUSTOM};
	public static final String[][] SOURCES = {
			//ASSAY 
			{"ACEA", "Attagene", "BioSeek", "Cellumen", "CellzDirect", "Gentronix", "NCGC", "Novascreen", "Solidus"},
			//PATHWAY
			{"PS_Gene", "PS_GO_1", "PS_GO_0", "PS_Ingenuity_1", "PS_Ingenuity_0", "PS_KEGG_1", "PS_KEGG_0", "PS_PathwayCommons_1", "PS_PathwayCommons_0", "PS_Phenotype_M", "PS_Phenotype_OMIM"},
			//ChemProp
			{"EPISuite", "LeadScope", "MN_MetabogenRxnClass", "MN_WholeMolecProperties", "QikProp", "StructureClassifiers", "PhysChem_derived", "ChemClass"},
			//Exposure
			{},
			//ToxRefDB
			{"ToxRefDB (DEV)", "ToxRefDB (SUB)", "ToxRefDB (MGR)", "ToxRefDB (CHR)"},
			//OTHER
			{"NHEERL MESC","NHEERL Zebrafish","NHEERL NeuroTox", "Docking OpenEye","Docking SimBioSys","LeadScope","OPP CARC"}
	}; 
	
       // public static String[] MISSING_DATA_COLORS = new String[]{"FFFFFF","E5E5E5","CCCCCC","B2B2B2","999999","7F7F7F","656565","4C4C4C","333333","191919"};
        //                                                        0       0.01    0.1      1.0      10      20       30         40      50      60      70          80      90      100
        public static String[] MISSING_DATA_COLORS = new String[]{"FFFFFF","E9E9E9","D4D4D4","BFBFBF","AAAAAA","949494","7F7F7F","6A6A6A","555555","3F3F3F","2A2A2A","151515","000000"};
        
	public static final Map<String, String[]> COLORS; 
		static{
			Map<String, String[]> smap = new HashMap<String, String[]>();
                        //green
			smap.put(ToxPiConfig.ASSAY, new String[] { "003300", "063B02", "0C4304",
				"124B06", "185308", "1E5B0A", "24630C", "2A6C0E", "307410",
				"377C12", "3D8414", "438C16", "499418", "4F9D1A", "55A51C",
				"5BAD1E", "61B520", "68BD22", "6EC524", "74CE26", "7AD628",
				"80DE2A", "86E62C", "8CEE2E", "92F630", "99FF33" });
                        //blue
			smap.put(ToxPiConfig.PATHWAY, new String[] { "000066", "00287E", "001472",
				"005196", "000A6C", "003384", "003D8A", "004790", "001E78",
				"005B9D", "0066A3", "0070A9", "007AAF", "0084B5", "008EBB",
				"0099C1", "00A3C7", "00ADCE", "00B7D4", "00C1DA", "00CCE0",
				"00D6E6", "00E0EC", "00EAF2", "00F4F8", "00FFFF" });
                        //orange
			smap.put(ToxPiConfig.CHEMPROP, new String[] { "CC6600", "CE6C04", "D07208",
				"D2780C", "D47E10", "D68414", "D88A18", "DA901C", "DC9620",
				"DE9D24", "E0A328", "E2A92C", "E4AF30", "E6B535", "E8BB39",
				"EAC13D", "ECC741", "EECE45", "F0D449", "F2DA4D", "F4E051",
				"F6E655", "F8EC59", "FAF25D", "FCF861", "FFFF66" });
                        //grey
			smap.put(ToxPiConfig.EXPOSURE, new String[] { "333333", "4B4B4B", "3F3F3F",
				"454545", "393939", "515151", "575757", "5D5D5D", "636363",
				"6A6A6A", "707070", "767676", "7C7C7C", "828282", "888888",
				"8E8E8E", "949494", "9B9B9B", "A1A1A1", "A7A7A7", "ADADAD",
				"B3B3B3", "B9B9B9", "BFBFBF", "C5C5C5", "CCCCCC" });
                        //purple
			smap.put(ToxPiConfig.TOXREFDB, new String[] { "990066", "A9207E", "A11072",
				"A51878", "9D086C", "AD2884", "B1308A", "B53990", "B94196",
				"BD499D", "C151A3", "C559A9", "C961AF", "CE6AB5", "D272BB",
				"D67AC1", "DA82C7", "DE8ACE", "E292D4", "E69BDA", "EAA3E0",
				"EEABE6", "F2B3EC", "F6BBF2", "FAC3F8", "FFCCFF" });
                        //dark red
			smap.put(ToxPiConfig.CUSTOM, new String[] { "330000", "3B0606", "430C0C",
				"4B1212", "531818", "5B1E1E", "632424", "6C2A2A", "743030",
				"7C3737", "843D3D", "8C4343", "944949", "9D4F4F", "A55555",
				"AD5B5B", "B56161", "BD6868", "C56E6E", "CE7474", "D67A7A",
				"DE8080", "E68686", "EE8C8C", "F69292", "FF9999" });
			COLORS = Collections.unmodifiableMap(smap);
		}
   
}
