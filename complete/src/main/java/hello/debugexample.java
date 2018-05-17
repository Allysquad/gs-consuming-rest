package hello;

public class debugexample {

    String exampleJSON = "{\n" +
            "  \"id\": 123,\n" +
            "  \"name\": \"Pankaj\",\n" +
            "  \"permanent\": true,\n" +
            "  \"address\": {\n" +
            "    \"street\": \"Albany Dr\",\n" +
            "    \"city\": \"San Jose\",\n" +
            "    \"zipcode\": 95129\n" +
            "  },\n" +
            "  \"phoneNumbers\": [\n" +
            "    123456,\n" +
            "    987654\n" +
            "  ],\n" +
            "  \"role\": \"Manager\",\n" +
            "  \"cities\": [\n" +
            "    \"Los Angeles\",\n" +
            "    \"New York\"\n" +
            "  ],\n" +
            "  \"properties\": {\n" +
            "    \"age\": \"29 years\",\n" +
            "    \"salary\": \"1000 USD\"\n" +
            "  }\n" +
            "}";
}



class Address {

    private String street;
    private String city;
    private int zipcode;

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public int getZipcode() {
        return zipcode;
    }
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString(){
        return getStreet() + ", "+getCity()+", "+getZipcode();
    }
}

 class Employee {

    private int id;
    private String name;
    private boolean permanent;
    private Address address;
    private long[] phoneNumbers;
    private String role;
    private List<String> cities;
    private Map<String, String> properties;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isPermanent() {
        return permanent;
    }
    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public long[] getPhoneNumbers() {
        return phoneNumbers;
    }
    public void setPhoneNumbers(long[] phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("***** Employee Details *****\n");
        sb.append("ID="+getId()+"\n");
        sb.append("Name="+getName()+"\n");
        sb.append("Permanent="+isPermanent()+"\n");
        sb.append("Role="+getRole()+"\n");
        sb.append("Phone Numbers="+Arrays.toString(getPhoneNumbers())+"\n");
        sb.append("Address="+getAddress()+"\n");
        sb.append("Cities="+Arrays.toString(getCities().toArray())+"\n");
        sb.append("Properties="+getProperties()+"\n");
        sb.append("*****************************");

        return sb.toString();
    }
    public List<String> getCities() {
        return cities;
    }
    public void setCities(List<String> cities) {
        this.cities = cities;
    }
    public Map<String, String> getProperties() {
        return properties;
    }
    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}

public class JacksonObjectMapperExample {

    public static void main(String[] args) throws IOException {

        //read json file data to String
        byte[] jsonData = Files.readAllBytes(Paths.get("employee.txt"));

        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        //convert json string to object
        Employee emp = objectMapper.readValue(jsonData, Employee.class);

        System.out.println("Employee Object\n"+emp);

        //convert Object to json string
        Employee emp1 = createEmployee();
        //configure Object mapper for pretty print
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        //writing to console, can write to any output stream such as file
        StringWriter stringEmp = new StringWriter();
        objectMapper.writeValue(stringEmp, emp1);
        System.out.println("Employee JSON is\n"+stringEmp);
    }



        public static Employee createEmployee() {

            Employee emp = new Employee();
            emp.setId(100);
            emp.setName("David");
            emp.setPermanent(false);
            emp.setPhoneNumbers(new long[] { 123456, 987654 });
            emp.setRole("Manager");

            Address add = new Address();
            add.setCity("Bangalore");
            add.setStreet("BTM 1st Stage");
            add.setZipcode(560100);
            emp.setAddress(add);

            List<String> cities = new ArrayList<String>();
            cities.add("Los Angeles");
            cities.add("New York");
            emp.setCities(cities);

            Map<String, String> props = new HashMap<String, String>();
            props.put("salary", "1000 Rs");
            props.put("age", "28 years");
            emp.setProperties(props);

            return emp;
        }

    }
}