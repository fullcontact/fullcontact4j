package com.fullcontact.api.libs.fullcontact4j.http.cardreader.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Skylar Lowery (skylar@fullcontact.com)
 * @date 4/9/14
 *
 * The CardReader API returns a list of string
 * key references that identify unverified fields
 * (basically diff locations between the
 * verified 'contact' field and the 'unverifiedContact'
 * field). It was originally intended to be a visual
 * tool (human readable) for debugging, but we quickly
 * realized it could be useful to use programmatically.
 *
 * This class helps us better identify those fields by
 * structuring them with a key, and possibly an index
 * (for fields that are lists of items, like urls).
 * Future versions of the Card Reader API will
 * support a much better JSON structure for identifying
 * diffs, but for now, this helps to identify what
 * exactly is unverified, if one desires.
 *
 * An example use would be that the first URL
 * coming back from Card Reader API is unverified, and
 * only shows up in the unverifiedContact. If this is true,
 * the unverifiedFields would contain a string 'urls[0]' meaning
 * the 0 index of the urls collection in the unverifiedContact is
 * what is missing from the verifiedContact ('contact' field)
 * in the api response. To quickly identify this, we gather
 * the unverifiedFields in the uploaded result,
 * and it should contain an {@link UnverifiedField} item
 * with the key 'urls' and the index 0;
 *
 * Now we can look up unverifiedContact.getUrls().get(0)
 * and see the unverified value for that entry.
 *
 * This is not a *great* way to do this because you'll likely
 * need some kind of switch statement or abstract hierarchy to
 * decide which item you gather from the unverifiedContact object,
 * but it is something better than nothing, if you want such functionality.
 *
 * Also note that the index field can and will be null for
 * fields that are not collections. An example is 'givenName'.
 * The key will be 'givenName' and the index will be null.
 * Another reason to reconsider using this in too much detail.
 *
 */
public class UnverifiedField {

    private static final Pattern unverifiedPattern = Pattern.compile("([a-zA-Z]+)(?:\\[([\\d]+)\\])?");

    private String key;
    private Integer index;

    public UnverifiedField(String key, Integer index) {
        this.key = key;
        this.index = index;
    }

    public static UnverifiedField parse(String s) throws InvalidUnverifiedFieldException {
        if (s == null) return null;
        try {
            Matcher matcher = unverifiedPattern.matcher(s);
            if (matcher.matches())
                return new UnverifiedField(
                        matcher.group(1),
                        matcher.group(2) != null ? Integer.valueOf(matcher.group(2)) : null
                );
            else return null;
        } catch (Exception e) {
            if (s == null) s = "null";
            throw new InvalidUnverifiedFieldException(s, e);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UnverifiedField{");
        sb.append("key='").append(key).append('\'');
        sb.append(", index=").append(index);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnverifiedField that = (UnverifiedField) o;

        if (index != null ? !index.equals(that.index) : that.index != null) return false;
        if (key != null ? !key.equals(that.key) : that.key != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (index != null ? index.hashCode() : 0);
        return result;
    }

    public String getKey() {

        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
