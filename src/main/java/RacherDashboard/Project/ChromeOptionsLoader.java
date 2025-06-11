package RacherDashboard.Project;

import com.google.gson.*;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileReader;
import java.util.*;

public class ChromeOptionsLoader {

    public static ChromeOptions loadOptionsFromJson(String jsonPath) {
        ChromeOptions options = new ChromeOptions();

        try (FileReader reader = new FileReader(jsonPath)) {
            Gson gson = new Gson();
            JsonObject json = gson.fromJson(reader, JsonObject.class);

            if (json == null || !json.has("options")) {
                System.err.println("❌ 'options' key not found in JSON config.");
                return options;
            }

            JsonObject opts = json.getAsJsonObject("options");

            // Handle args
            if (opts.has("args")) {
                JsonArray args = opts.getAsJsonArray("args");
                for (JsonElement arg : args) {
                    options.addArguments(arg.getAsString());
                }
            }

            // Handle experimentalOptions
            if (opts.has("experimentalOptions")) {
                JsonObject experimental = opts.getAsJsonObject("experimentalOptions");
                for (Map.Entry<String, JsonElement> entry : experimental.entrySet()) {
                    JsonElement value = entry.getValue();
                    Object val;

                    if (value.isJsonArray()) {
                        List<String> list = new ArrayList<>();
                        for (JsonElement e : value.getAsJsonArray()) {
                            list.add(e.getAsString());
                        }
                        val = list;
                    } else if (value.isJsonPrimitive()) {
                        JsonPrimitive primitive = value.getAsJsonPrimitive();
                        if (primitive.isBoolean()) {
                            val = primitive.getAsBoolean();
                        } else if (primitive.isNumber()) {
                            val = primitive.getAsNumber();
                        } else {
                            val = primitive.getAsString();
                        }
                    } else if (value.isJsonObject()) {
                        val = gson.fromJson(value, Map.class);
                    } else {
                        continue;
                    }

                    options.setExperimentalOption(entry.getKey(), val);
                }
            }

            // Handle prefs
            if (opts.has("prefs")) {
                JsonObject prefs = opts.getAsJsonObject("prefs");
                Map<String, Object> prefsMap = new HashMap<>();
                for (Map.Entry<String, JsonElement> entry : prefs.entrySet()) {
                    JsonElement val = entry.getValue();
                    if (val.isJsonPrimitive()) {
                        JsonPrimitive prim = val.getAsJsonPrimitive();
                        if (prim.isBoolean()) {
                            prefsMap.put(entry.getKey(), prim.getAsBoolean());
                        } else if (prim.isNumber()) {
                            prefsMap.put(entry.getKey(), prim.getAsNumber());
                        } else {
                            prefsMap.put(entry.getKey(), prim.getAsString());
                        }
                    }
                }
                options.setExperimentalOption("prefs", prefsMap);
            }

        } catch (Exception e) {
            System.err.println("❌ Failed to load Chrome options from JSON: " + e.getMessage());
            e.printStackTrace();
        }

        return options;
    }
}
