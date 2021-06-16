const prompt = require("prompt-async");
const puppeteer = require('puppeteer');
const fs = require('fs');
const url = []
const str =[]
const num = 5
const loopNum = 2*num
let i =0
async function example_async() // Available only with `prompt-async`!
{
    // Start the prompt.
    prompt.start();
    const {inputUrl} = await prompt.get(["inputUrl"]);
    const url1 = inputUrl.split(",");
    while(i<loopNum){
        url.push(url1[i]);
        str.push(url1[i+1]);
        i=i+2;

    }
    // console.log(url)
    // console.log(str)        
    
    const browser = await puppeteer.launch({headless:false});
    const page = await browser.newPage()
    var j=0;
    while(j<num){
      
      await page.goto(url[j]);
      const outerHtml = await page.$eval('body',el => el.outerHTML)
      fs.writeFile(str[j], outerHtml, { flag: 'a+' }, err => {})
      j++;
    }
    await browser.close();
    //console.log("Success");
}
 
async function error_handling_async()
{
    try
    {
        await example_async();
    }
    catch(error)
    {
        console.error("An error occurred: ", error);
    }
}
 
error_handling_async();


