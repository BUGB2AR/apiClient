package com.providertechapi.cliente.domain.builder;

import com.providertechapi.cliente.infra.config.JsonConfiguration;
import org.springframework.util.Assert;
import org.slf4j.Logger;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.Map;

public class Log5wBuilder {

    public static class ReadyToLog {
        private Map<String, String> params = new LinkedHashMap<>();

        public ReadyToLog(String method, String whatIsHappening, Map<String, String> info) {
            params.put("method:", method);
            params.put("whatIsHappening:", whatIsHappening);
            params.putAll(info);
        }

        public ReadyToLog addInfo(String key, Object info) {
            Assert.hasText(key, "The key cannot be empty");
            Assert.notNull(info, "The info to log cannot be null");
            Assert.isNull(params.get(key), "The key has already been added");
            params.put(key, info.toString());
            return this;
        }

        public String debug(@NotNull Logger logger) {
            String json = JsonConfiguration.json(this.params);
            logger.debug(json);
            return json;
        }

        public String info(@NotNull Logger logger) {
            String json = JsonConfiguration.json(this.params);
            logger.info(json);
            return json;
        }

        public String error(@NotNull Logger logger) {
            String json = JsonConfiguration.json(this.params);
            logger.error(json);
            return json;
        }

        public String message() {
            return JsonConfiguration.json(this.params);
        }
    }

    public static class WhatIsHappening {
        private String method;
        private String whatIsHappening;
        private Map<String, String> additionalInfos = new LinkedHashMap<>();

        public WhatIsHappening(String method, String whatIsHappening) {
            this.method = method;
            this.whatIsHappening = whatIsHappening;
        }

        public ReadyToLog addInfo(String key, Object info) {
            Assert.hasText(key, "The key cannot be empty");
            Assert.notNull(info, "The info to log cannot be null");
            Assert.isNull(additionalInfos.get(key), "The key has already been added");

            additionalInfos.put(key, info.toString());
            return new ReadyToLog(method, whatIsHappening, additionalInfos);
        }
    }

    public static class Method {
        private String method;

        public Method(String method) {
            Assert.hasText(method, "The method cannot be empty");
            this.method = method;
        }

        public WhatIsHappening whatIsHappening(String whatIsHappening) {
            Assert.hasText(whatIsHappening, "The description cannot be empty");
            return new WhatIsHappening(method, whatIsHappening);
        }

        public static Method method(String method) {
            return new Method(method);
        }

        public static Method method() {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            return new Method(stackTrace[2].getMethodName());
        }
    }
}
