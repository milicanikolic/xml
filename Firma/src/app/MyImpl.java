package app;

import javax.jws.WebService;

@WebService(endpointInterface = "app.MyInterface")
public class MyImpl implements MyInterface{

	@Override
	public String getHelloWorldAsString(String name) {
		return "Hello World JAX-WS " + name;
	}

}
