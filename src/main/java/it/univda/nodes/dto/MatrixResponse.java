package it.univda.nodes.dto;

import java.util.List;
import java.util.Map;

public class MatrixResponse {
    private List<String> headers; // All competences/interests
    private Map<String, Map<String, Long>> rows;

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setRows(Map<String, Map<String, Long>> rows) {
        this.rows = rows;
    }

    public Map<String, Map<String, Long>> getRows() {
        return rows;
    }
}
