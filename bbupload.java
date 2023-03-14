RestTemplate restTemplate = new RestTemplate();

// Set up authentication headers
HttpHeaders headers = new HttpHeaders();
headers.set("Authorization", "Bearer " + accessToken);

// Construct the API endpoint URL
String apiUrl = "https://api.bitbucket.org/2.0/repositories/{owner}/{repo}/src/{branch}/{filepath}";
apiUrl = apiUrl.replace("{owner}", owner)
               .replace("{repo}", repo)
               .replace("{branch}", branch)
               .replace("{filepath}", filepath);

// Construct the JSON payload
JSONObject payload = new JSONObject();
payload.put("message", commitMessage);
payload.put("content", Base64.encodeBase64String(fileContents.getBytes()));
payload.put("author", authorName + " <" + authorEmail + ">");
payload.put("committer", committerName + " <" + committerEmail + ">");

// Send the HTTP POST request
HttpEntity<String> request = new HttpEntity<>(payload.toString(), headers);
ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

// Process the response
if (response.getStatusCode() == HttpStatus.CREATED) {
    JSONObject commitInfo = new JSONObject(response.getBody());
    System.out.println("File committed with commit ID: " + commitInfo.getString("hash"));
} else {
    System.err.println("Failed to commit file: " + response.getStatusCode() + " " + response.getBody());
}
