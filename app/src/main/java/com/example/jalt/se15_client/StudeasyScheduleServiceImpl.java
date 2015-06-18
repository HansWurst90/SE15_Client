package com.example.jalt.se15_client;


import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Date;
import java.util.List;

import common.BooleanResponse;
import common.Customer;
import common.HomeworkListResponse;
import common.IStudeasyScheduleService;
import common.LessonByIDResponse;
import common.LessonListResponse;
import common.ReturncodeResponse;
import common.UserLoginResponse;


/**
 * Diese Klasse enthaelt die Verbindung der App mit dem Xbank-Webservice.
 */
public class StudeasyScheduleServiceImpl implements IStudeasyScheduleService {
    /**
     * Namespace is the targetNamespace in the WSDL.
     */
    private static final String NAMESPACE = "http://schedulemanager.studeasy.de/";

    /**
     * The WSDL URL. Its value is the location attribute of the soap:address element for a port
     * element in a WSDL. Unless the web service is also hosted on the Android device, the hostname 
     * should not be specified as localhost, because the application runs on the Android device while 
     * the web service is hosted on the localhost server. Specify hostname as the IP address of the 
     * server hosting the web service (or "10.0.2.2 instead of 'localhost' when running in the emulator). 
     */
    private static final String URL = "http://10.60.70.6:8080/studeasy/StudeasyScheduleService?WSDL";
    private int sessionId;

    @Override
    public UserLoginResponse login(int personID, String password) throws Exception{
        UserLoginResponse result = null;
        String METHOD_NAME = "login";
        SoapObject response = null;
        try {
            response = executeSoapAction(METHOD_NAME, personID, password);
            this.sessionId = Integer.parseInt(response.getPrimitivePropertySafelyAsString("sessionID"));
            if (sessionId != 0) {
                result = new UserLoginResponse();
                result.setSessionID(sessionId);
                return result;
            }
            else {
                throw new Exception("Login not successful!");
            }
        } catch (SoapFault e) {
            throw new Exception(e.getMessage());
        }
    }


    @Override
    public ReturncodeResponse logout(int sessionID) throws Exception {
        String METHOD_NAME = "logout";
        try {
            SoapObject response = executeSoapAction(METHOD_NAME, sessionId);
        }
        catch (SoapFault e) {
            throw new Exception(e.getMessage());
        }
        return new ReturncodeResponse();
    }

    public ReturncodeResponse createHomework(int sessionID, int lessonID, String description)
    {
        return new ReturncodeResponse();
    }

    public BooleanResponse removeHomework(int sessionID, int homeworkID)
    {
        return new BooleanResponse();
    }

    public LessonListResponse getLessonsByDate(int sessionID, Date date)
    {
        return new LessonListResponse();
    }

    public LessonByIDResponse findLessonById(int lessonID)
    {
        return new LessonByIDResponse();
    }

    public LessonListResponse getLessonsBySubject(int subjectID,int courseID, Date startDate, Date endDate)
    {
        return new LessonListResponse();
    }

    public HomeworkListResponse getHomeworksForPupil(int sessionID, Date startDate, Date endDate)
    {
        return new HomeworkListResponse();
    }

    /**
     * Diese Methode delegiert einen Methodenaufruf an den hinterlegten WebService.
     * @param methodName
     * @return
     */
    private SoapObject executeSoapAction(String methodName, Object... args) throws SoapFault {

        Object result = null;
		
	    /* Create a org.ksoap2.serialization.SoapObject object to build a SOAP request. Specify the namespace of the SOAP object and method
	     * name to be invoked in the SoapObject constructor.
	     */
        SoapObject request = new SoapObject(NAMESPACE, methodName);
	    
	    /* The array of arguments is copied into properties of the SOAP request using the addProperty method. */
        for (int i=0; i<args.length; i++) {
            request.addProperty("arg" + i, args[i]);
        }
	    
	    /* Next create a SOAP envelop. Use the SoapSerializationEnvelope class, which extends the SoapEnvelop class, with support for SOAP 
	     * Serialization format, which represents the structure of a SOAP serialized message. The main advantage of SOAP serialization is portability.
	     * The constant SoapEnvelope.VER11 indicates SOAP Version 1.1, which is default for a JAX-WS webservice endpoint under JBoss.
	     */
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
	    
	    /* Assign the SoapObject request object to the envelop as the outbound message for the SOAP method call. */
        envelope.setOutputSoapObject(request);
	    
	    /* Create a org.ksoap2.transport.HttpTransportSE object that represents a J2SE based HttpTransport layer. HttpTransportSE extends
	     * the org.ksoap2.transport.Transport class, which encapsulates the serialization and deserialization of SOAP messages.
	     */
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
	        /* Make the soap call using the SOAP_ACTION and the soap envelop. */
            List<HeaderProperty> reqHeaders = null;

            @SuppressWarnings({"unused", "unchecked"})
            //List<HeaderProperty> respHeaders = androidHttpTransport.call(NAMESPACE + methodName, envelope, reqHeaders);
            //fuehrt zu CXF-Fehler! neue Version ohne SOAP-Action funktioniert:
            List<HeaderProperty> respHeaders = androidHttpTransport.call("", envelope, reqHeaders);
	
	        /* Get the web service response using the getResponse method of the SoapSerializationEnvelope object.
	         * The result has to be cast to SoapPrimitive, the class used to encapsulate primitive types, or to SoapObject.
	         */
            result = envelope.getResponse();

            if (result instanceof SoapFault) {
                throw (SoapFault) result;
            }
        }
        catch (SoapFault e) {
            e.printStackTrace();
            throw e;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return (SoapObject) result;
    }
}
