package persistence;

import org.json.JSONObject;

//Provides methods necessary for converting objects to JSON
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
