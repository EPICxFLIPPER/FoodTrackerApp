package conversions;

import exception.InvalidConversionException;

import java.util.HashMap;
import java.util.Map;

public class UnitConverter {
    private Map<String,Double> conversion;
    private static UnitConverter theConverter;

    private UnitConverter() {
        conversion = new HashMap<>();
        initMap();
    }

    public static UnitConverter getInstance() {
        if (theConverter == null) {
            theConverter = new UnitConverter();
        }

        return theConverter;
    }


    //EFFECTS: Sets up the map with all conversions and factors
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void initMap() {
        conversion.put("GtoKG",.001D);
        conversion.put("GtoMG",1000D);
        conversion.put("GtoG",1D);
        conversion.put("GtoLB",0.00220462D);


        conversion.put("KGtoKG",1D);
        conversion.put("KGtoG",1000D);
        conversion.put("KGtoMG",1000000D);
        conversion.put("KGtoLB",2.20462D);

        conversion.put("MGtoMG",1D);
        conversion.put("MGtoKG",.0000001D);
        conversion.put("MGtoG",.001D);
        conversion.put("MGtoLB",0.0000022D);

        conversion.put("LBtoLB",1D);
        conversion.put("LBtoMG",453592D);
        conversion.put("LBtoG",453.592D);
        conversion.put("LBtoKG",0.453592);

        conversion.put("CLOVEtoCLOVE",1D);
        conversion.put("UNITtoUNIT",1D);

        conversion.put("TBSPtoTBSP",1D);
        conversion.put("TBSPtoTSP",3D);
        conversion.put("TBSPtoGAL",0.00390);
        conversion.put("TBSPtoOZ",.5D);
        conversion.put("TBSPtoCUP",0.0625D);

        conversion.put("TSPtoTSP",1D);
        conversion.put("TSPtoTBSP",.33333D);
        conversion.put("TSPtoGAL",0.00130);
        conversion.put("TSPtoOZ",.1666D);
        conversion.put("TSPtoCUP",0.02083D);

        conversion.put("GALtoGAL",1D);
        conversion.put("GALtoTBSP",256D);
        conversion.put("GALtoTSP",768D);
        conversion.put("GALtoCUP",16D);
        conversion.put("GALtoOZ",128D);

        conversion.put("OZtoOZ",1D);
        conversion.put("OZtoTBSP",2D);
        conversion.put("OZtoTSP",6D);
        conversion.put("OZtoGAL",.0078);
        conversion.put("OZtoCUP",.125D);

        conversion.put("CUPtoCUP",1D);
        conversion.put("CUPtoTBSP",16D);
        conversion.put("CUPtoTSP",48D);
        conversion.put("CUPtoOZ",8D);
        conversion.put("CUPtoGAL",0.0625D);
    }

    //Effects: Returns the conversion factor for one unit to the other
    public double convertFactor(String from, String to) throws InvalidConversionException {
        String key = createKey(from,to);

        try {
            double factor = conversion.get(key);
        } catch (NullPointerException e) {
            throw new InvalidConversionException();
        }

        return conversion.get(key);

    }

    //Effects: uses the given units to make the key to find factor from map.
    private String createKey(String from, String to) {
        return from.toUpperCase() + "to" + to.toUpperCase();
    }
}
