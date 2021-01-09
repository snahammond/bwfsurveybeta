const aws = require('aws-sdk');
const docClient = new aws.DynamoDB.DocumentClient();

exports.handler = event => {

  event.Records.forEach(record => {    
    if(record.eventSourceARN.includes("InitialSurvey")){
      if(record.dynamodb.NewImage && !record.dynamodb.OldImage){
        //this is a new insert
        increaseTotal("1");
      } else if (record.dynamodb.NewImage && record.dynamodb.OldImage) {
        // old and new item, meaning record has been updated. check if you need to increase a count or not
      } else {
        // no new item, meaning record has been deleted. you may need to decrement a count
        decreaseTotal("1");
      }      
    }else if(record.eventSourceARN.includes("FollowUpSurvey")){
      if(record.dynamodb.NewImage && !record.dynamodb.OldImage){
        //this is a new insert
        increaseTotal("2");
      }else if (record.dynamodb.NewImage && record.dynamodb.OldImage) {
        // old and new item, meaning record has been updated. check if you need to increase a count or not
      } else {
        // no new item, meaning record has been deleted. you may need to decrement a count
        decreaseTotal("2");
      }        
    }else if(record.eventSourceARN.includes("CommunityWaterTest")){
      if(record.dynamodb.NewImage && !record.dynamodb.OldImage){
        //this is a new insert
        increaseTotal("3");
      }else if (record.dynamodb.NewImage && record.dynamodb.OldImage) {
        // old and new item, meaning record has been updated. check if you need to increase a count or not
      } else {
        // no new item, meaning record has been deleted. you may need to decrement a count
        decreaseTotal("3");
      }        
    }else if(record.eventSourceARN.includes("HouseholdWaterTest")){
      if(record.dynamodb.NewImage && !record.dynamodb.OldImage){
        //this is a new insert
        increaseTotal("4");
      }else if (record.dynamodb.NewImage && record.dynamodb.OldImage) {
        // old and new item, meaning record has been updated. check if you need to increase a count or not
      } else {
        // no new item, meaning record has been deleted. you may need to decrement a count
        decreaseTotal("4");
      }        
    }else if(record.eventSourceARN.includes("HealthCheckSurvey")){
      if(record.dynamodb.NewImage && !record.dynamodb.OldImage){
        //this is a new insert
        increaseTotal("5");
      }else if (record.dynamodb.NewImage && record.dynamodb.OldImage) {
        // old and new item, meaning record has been updated. check if you need to increase a count or not
      } else {
        // no new item, meaning record has been deleted. you may need to decrement a count
        decreaseTotal("5");
      }        
    }
  });
  
};


function increaseTotal(id){
  const params = {
    TableName: "BWFSURVEYTOTALS-bfnddhipvvaallvpkd3uffhvlq-dev",
    Key: {
      "id": id          
    },
    UpdateExpression: "set #total = #total + :val",
    ExpressionAttributeNames: { "#total": "total" },
    ExpressionAttributeValues: {
      ":val": 1
    }
  };
  console.log("going to do update with param "+ params);
  docClient.update(params, function(err, data) {
    if (err) {
      console.log("error occured " + err);
      return Promise.resolve('Error Successfully processed DynamoDB record added aws-sdk');
    }        
    else{
      console.log("update done " + data);
      return Promise.resolve('Successfully processed DynamoDB record added aws-sdk');
    }         
  });
}


function decreaseTotal(id){
  const params = {
    TableName: "BWFSURVEYTOTALS-bfnddhipvvaallvpkd3uffhvlq-dev",
    Key: {
      "id": id          
    },
    UpdateExpression: "set #total = #total - :val",
    ExpressionAttributeNames: { "#total": "total" },
    ExpressionAttributeValues: {
      ":val": 1
    }
  };
  console.log("going to do update with param "+ params);
  docClient.update(params, function(err, data) {
    if (err) {
      console.log("error occured " + err);
      return Promise.resolve('Error Successfully processed DynamoDB record added aws-sdk');
    }        
    else{
      console.log("update done " + data);
      return Promise.resolve('Successfully processed DynamoDB record added aws-sdk');
    }         
  });
}