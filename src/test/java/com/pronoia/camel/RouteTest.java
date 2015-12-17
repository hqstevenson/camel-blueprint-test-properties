package com.pronoia.camel;

import org.apache.camel.EndpointInject;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;

import org.junit.Test;

import java.util.Dictionary;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class RouteTest extends CamelBlueprintTestSupport {
    @EndpointInject(uri = "mock://result")
    MockEndpoint result;

    String DEFAULT_VALUE = "Defaut Value from property-placeholder";
    String INJECTED_VALUE = "New Value from RouteTest";

    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/blueprint.xml";
    }

    /*
    @Override
    protected Properties useOverridePropertiesWithPropertiesComponent() {
        Properties props = new Properties();
        props.setProperty("helloBean.say", INJECTED_VALUE);

        return props;
    }
    */

    /*
    */
    @Override
    protected String useOverridePropertiesWithConfigAdmin(Dictionary props) throws Exception {

        props.put("helloBean.say", INJECTED_VALUE);

        return "BlueprintBugTest";
    }

    @Test
    public void testValueInjectedIntoBean() throws Exception {
        result.expectedBodiesReceived(INJECTED_VALUE);

        template.sendBody("direct://trigger", "bean");

        assertMockEndpointsSatisfied(10, TimeUnit.SECONDS);
    }

    @Test
    public void testValueInjectedIntoRoute() throws Exception {
        result.expectedBodiesReceived(INJECTED_VALUE);

        template.sendBody("direct://trigger", "Dummy value to trigger otherwise in choice");

        assertMockEndpointsSatisfied(10, TimeUnit.SECONDS);
    }
}
