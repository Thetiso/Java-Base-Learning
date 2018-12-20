## 使用rest传递参数
[详解!restTemplate使用实例(各种情况都包含!!)](https://blog.csdn.net/zty1317313805/article/details/80096584)

#### 1. 传递cookie
```
    HttpHeaders headers = new HttpHeaders();
    List<String> cookies = new ArrayList<>();
    cookies.add("JSESSIONID=" + Strings.nullToEmpty(jsessionId));
    cookies.add("token=" + Strings.nullToEmpty(token));
    headers.put(HttpHeaders.COOKIE,cookies);
    HttpEntity request = new HttpEntity(null, headers);
    ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
```

#### 2. POST 表单
```
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
    map.add("title", title);
    map.add("desc", desc);
    map.add("userid", toUserId);
    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
    ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
```
#### 3. POST JSON
```
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));
    HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
    ResponseEntity<String> resp = restTemplate.postForEntity(url,entity,String.class);
```

#### 4. url POST
```
    String template = baseUrl + "/demo?app={0}&userId={1}";
    String url = MessageFormat.format(template,app,userId);
    return restTemplate.postForEntity(url,null,String.class);
```

#### 5. 请求图片
```
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
	HttpEntity<String> entity = new HttpEntity<String>(headers);
	ResponseEntity<byte[]> response = restTemplate.exchange(url,HttpMethod.GET, entity, byte[].class);
	byte[] imageBytes = response.getBody();
```
## 在自定义servlet中使用rest
1. 使用@WebServlet(urlPatterns = "/HttpEngine")注解自定义servlet类
2. 使用@ServletComponentScan在程序启动时扫描，使spring管理自定义servelet
3. 可以直接在自定义servelt中@autoWired来获取rest
4. 底层方法可以通过以下方法获取：
```
@Component
public class ApplicationContextAwareImpl implements ApplicationContextAware {

	private static ApplicationContext context = null; 

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.applicationContext = context = applicationContext;
    }
    public static ApplicationContext getApplicationContext() {  
        return context;  
    }  
}

...

public class ApplicationContextUtils {
	public static ApplicationContext applicationContext;
	
    public static Object get(String name) {
        return applicationContext.getBean(name);
    }
    public static Object get(Class<?> clazz) {
        return applicationContext.getBean(clazz);
    }
    public static boolean has(String name) {
        return applicationContext.containsBean(name);
    }
}
...

//获取方法
	RestTemplate restTemplate = (RestTemplate)ApplicationContextUtils.get(RestTemplate.class);
	if (restTemplate != null) {
		String testRestTemplate = restTemplate.getForObject("http://SERVICE-TEST/hi?name=abcedf",String.class);
		System.out.println(restTemplate);
		System.out.println(testRestTemplate);
	}
```