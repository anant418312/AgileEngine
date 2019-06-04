Feature: Creating/Editing/Deleting Posts 

Scenario Outline:
User's ability to create, edit and delete the post on a Facebook test page 
	Given "<BrowserType>" is opened to launch the "<url>"
#	Given Browser is opened to launch the url 
	When The user logs in "<username>" and "<password>"
	And navigates to the test page 
	And create a post with "<Post>" and make sure it got posted
	And edit the post with "<PostUpdate>" and make sure it got added to the "<Post>"
	Then delete the post
		Examples:
		|BrowserType|url|username|password|Post|PostUpdate|
		|firefox|https://www.facebook.com|anant_qvhbphh_selenium@tfbnw.net|1111111pP|This is my first post.|This is my edited post.|