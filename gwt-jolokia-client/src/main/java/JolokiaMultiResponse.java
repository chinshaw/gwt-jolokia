import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.jolokia.client.JolokiaResponse;


public class JolokiaMultiResponse extends JavaScriptObject {

	protected JolokiaMultiResponse() {}
	
	
	public final native JsArray<JolokiaResponse> getResponses() /*-{
		return this.value;
	}-*/;
	
}
