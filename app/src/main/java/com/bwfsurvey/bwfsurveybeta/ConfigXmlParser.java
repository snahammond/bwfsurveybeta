package com.bwfsurvey.bwfsurveybeta;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ConfigXmlParser {
    private static final String ns = null;

    public List parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readConfigs(parser);
        } finally {
            in.close();
        }
    }

    private List readConfigs(XmlPullParser parser) throws XmlPullParserException, IOException {
        List entries = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "configs");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the config tag
            if (name.equals("config")) {
                entries.add(readConfig(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private Config readConfig(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "config");
        String type = null;
        String name = null;
        String value = null;
        String description = null;
        String childName = null;
        String childValue = null;
        String childDescription = null;
        String parentName = null;
        String parentValue = null;
        String parentDescription = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String tagname = parser.getName();
            if (tagname.equals("type")) {
                type = readTag(parser,"type");
            } else if (tagname.equals("name")) {
                name = readTag(parser,"name");
            } else if (tagname.equals("value")) {
                value = readTag(parser,"value");
            } else if (tagname.equals("desc")) {
                description = readTag(parser,"desc");
            }else if (tagname.equals("childname")) {
                childName = readTag(parser,"childname");
            }else if (tagname.equals("childvalue")) {
                childValue = readTag(parser,"childvalue");
            }else if (tagname.equals("childdesc")) {
                childDescription = readTag(parser,"childdesc");
            }else if (tagname.equals("parentname")) {
                parentName = readTag(parser,"parentname");
            }else if (tagname.equals("parentvalue")) {
                parentValue = readTag(parser,"parentvalue");
            }else if (tagname.equals("parentdesc")) {
                parentDescription = readTag(parser,"parentdesc");
            }else {
                skip(parser);
            }
        }
        return new Config(type, name, value, description, childName, childValue,childDescription, parentName, parentValue, parentDescription);
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    private String readTag(XmlPullParser parser, String tag) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, tag);
        String tagValue = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, tag);
        return tagValue;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
}
