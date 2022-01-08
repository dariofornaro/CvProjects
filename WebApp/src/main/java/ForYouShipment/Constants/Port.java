package ForYouShipment.Constants;

public enum Port {

    TRANSIT("In Transit", 0., 0.),
    LISBON("Lisbon",38.7020,-9.1734),
    PORTO("Porto",41.1830,-8.7000),
    ROTTERDAM("Rotterdam",51.8850,4.2867),
    NEW_YORK("New York",40.6677,-74.0407), 
    RIO_DE_JANEIRO("Rio de Janeiro",-22.8981,-43.1808), 
    CAPETOWN("Capetown",-33.9043,18.4301), 
    SHANGHAI("Shanghai",31.2198,121.4870), 
    SYDNEY("Sydney", -33.8462,151.2489), 
    COPENHAGEN("Copenhagen",55.6718,12.5817),
    LA("Los Angeles",33.7291858, -118.262015),
    GENOVA("Genova", 44.4222, 8.9052),
    MESSINA("Messina", 38.1943, 15.5505),
    CONSTANTA("Constanta", 44.131050, 28.649421),
    NEKO("Neko",-64.833333, -62.55);


    private final String name;
    private final Double latitude;
    private final Double longitude;
    
    
    /**
     * Constructor for the Port enum
     * @param name      Name of the port
     * @param latitude  Latidude in decimal degrees
     * @param longitude Longitude in decimal degrees
     */
    Port(String name, Double latitude, Double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString(){
        return name;
    }
    
    
    public static Port ofString(String name) throws Exception{
        name = name.substring(0,1).toUpperCase() + name.substring(1);
        for (Port p: Port.class.getEnumConstants()){
            if (p.toString().equals(name))
                return p;
        }
        throw new EnumConstantNotPresentException(Port.class, "Invalid port name");
    }

    public Double getLatitude(){
      return latitude;
    }

    public Double getLongitude(){
       return longitude;        
    }
    
    /**
     * Returns the number of Ports
     * @return Number of Ports as int
     */
    public static int countPorts(){
        return Port.values().length;
    }



    
}
