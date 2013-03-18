package com.skyworth.http.identifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.skyworth.entity.Program;
import com.skyworth.http.BaseHttp;

public final class ProgramHttp extends BaseHttp<ArrayList<Program>> {

    public static final String TAG = "ProgramHttp";

    public ProgramHttp() {
    }

    @Override
    protected ArrayList<Program> parse(InputStream is) {
        String responseContent = readResponseContent(is);
        if (null != responseContent) {
            return parsePrograms(responseContent);
        }
        return null;
    }

    /**
     * Read content from the input stream built on http response
     * 
     * @param is
     *            InputStream object
     * @return InputStream content
     */
    private String readResponseContent(InputStream is) {
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        // string builder that build the http response conent
        StringBuilder contentBuilder = new StringBuilder();
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line);
            }
        } catch (IOException e) {
            Log.e(TAG, "http response content read error!");
            return null;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                Log.e(TAG, "IO close error!", e);
                return null;
            }
        }
        if (contentBuilder.length() != 0) {
            return contentBuilder.toString();
        }
        return null;
    }

    /**
     * Passe JSON format data, returns Program list
     * 
     * @param jsonString
     *            json format string variable
     * @return program list
     */
    private ArrayList<Program> parsePrograms(String jsonString) {
        JSONObject jsonObject = null;
        JSONArray jsonArray = null;
        ArrayList<Program> programs = new ArrayList<Program>(1);
        Program program = null;
        try {
            jsonObject = new JSONObject(jsonString);
            jsonArray = jsonObject.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject subJson = jsonArray.getJSONObject(i);
                program = new Program();
                program.mId = subJson.getLong(Program.ID);
                program.mTitle = subJson.getString(Program.TITILE);
                program.mUrl = subJson.getString(Program.URL);
                program.mActors = subJson.getString(Program.ACTOR).split("\\|");
                program.mSubType = subJson.getString(Program.SUBTYPE).split(
                        "\\|");
                program.mDestription = subJson.getString(Program.DESCRIPTION);
                program.mThumbUrl = subJson.getString(Program.THUMB_URL);
                programs.add(program);
            }
        } catch (JSONException e) {
            Log.e(TAG, "jsonarray object create error", e);
        }
        return programs;
    }

}
