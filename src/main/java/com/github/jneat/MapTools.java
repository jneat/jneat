/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 by rumatoest at github.com
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE. 
 */
package com.github.jneat;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Vladislav Zablotsky
 */
public class MapTools {

    /**
     * Create Map<Object, Object> from key value method arguments
     *
     * @param keyOrValue key-value array first argument - key, next - value.
     * In a case there is no next value - null will be set
     * @return Not null
     */
    public static Map create(Object... keyOrValue) {
        Map map = new HashMap<>();
        for (int i = 0; i < keyOrValue.length; i += 2) {
            if (i + 1 >= keyOrValue.length) {
                map.put(keyOrValue[i], null);
            } else {
                map.put(keyOrValue[i], keyOrValue[i + 1]);
            }
        }
        return map;
    }

    /**
     * Create Map<String, String> from key value method arguments
     *
     * @param keyOrValue key-value array first argument - key, next - value.
     * In a case there is no next value - null will be set
     * @return Not null
     */
    public static Map<String, String> createStr(String... keyOrValue) {
        return (Map<String, String>)create((Object[])keyOrValue);
    }

    /**
     * Create Map<String, Object> from key value method arguments
     *
     * @param keyOrValue key-value array first argument - key (will be converted to string), next - value.
     * In a case there is no next value - null will be set
     * @return Not null
     */
    public static Map<String, Object> createStrKey(Object... keyOrValue) {
        Map map = new HashMap<>();
        for (int i = 0; i < keyOrValue.length; i += 2) {
            if (i + 1 >= keyOrValue.length) {
                map.put(String.valueOf(keyOrValue[i]), null);
            } else {
                map.put(String.valueOf(keyOrValue[i]), keyOrValue[i + 1]);
            }
        }
        return map;
    }

    /**
     * Create Properties object from key value method arguments.
     * Particular case for Map<String, String>
     *
     * @param keyOrValue key-value array first argument - key, next - value.
     * In a case there is no next value - null will be set
     * @return Not null
     */
    public static Properties createProperties(String... keyOrValue) {
        Properties props = new Properties();
        props.putAll(createStr(keyOrValue));
        return props;
    }
}
