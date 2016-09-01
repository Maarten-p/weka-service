package Associator;

import Main.Algorithm;

public class BuildModelPayload {


    private String[] options;
    private String query;
    private Algorithm algorithm;
    private String method;

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public boolean isValid() {
        Algorithm[] values = Algorithm.values();
        for (Algorithm value : values) {
            if (value == this.algorithm)
                return true;
        }
        return false;
    }

    public void insertDefaults() {

        if (algorithm == null)
            setAlgorithm(Algorithm.FPGROWTH);

        if (query == null || query.isEmpty()) {
            setQuery("prefix skosxl: <http://www.w3.org/2008/05/skos-xl#>\n" +
                    "prefix esco: <http://data.europa.eu/esco/model#>\n" +
                    "prefix mu: <http://mu.semte.ch/vocabularies/core/>" +
                    "select group_concat(distinct ?skillUuid; separator=\",\") as ?skillUuid  where {\n" +
                    "  graph <http://localhost:8890/DAV> {\n" +
                    "    ?s a esco:Occupation.\n" +
                    "    ?relation esco:isRelationshipFor ?s.\n" +
                    "    ?relation esco:refersConcept ?skill.\n" +
                    "    ?s mu:uuid ?uuid.\n" +
                    "    ?s skosxl:prefLabel / skosxl:literalForm ?label.\n" +
                    "    ?skill skosxl:prefLabel / skosxl:literalForm ?skilllabel.\n" +
                    "    ?skill mu:uuid ?skillUuid.\n" +
                    "    FILTER ( lang(?label) = \"en\" )\n" +
                    "    FILTER ( lang(?skilllabel) = \"en\" )\n" +
                    "  }\n" +
                    "}" +
                    "group by ?uuid ");
        }

        if (options == null) {
            String[] newOptions = new String[7];
            newOptions[0] = "-C";
            newOptions[1] = "0.85";
            newOptions[2] = "-M";
            newOptions[3] = "0.028";
            newOptions[4] = "-T";
            newOptions[5] = "0";
            newOptions[6] = "-S";
            setOptions(newOptions);
        }
    }

}