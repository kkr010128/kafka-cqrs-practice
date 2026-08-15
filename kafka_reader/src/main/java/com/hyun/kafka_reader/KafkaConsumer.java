package com.hyun.kafka_reader;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumer {
    @KafkaListener(topics = "cqrs-topic", groupId = "hyeon")
    public void consumer(String message) throws IOException {
        System.out.println("message:" + message);
        // JSON 파싱: JSON문자열로 온 것을 자바 객체로 변환
        JSONObject messageObj = new JSONObject(message);

        // JSON 파싱: JSON문자열로 온 것을 자바 객체로 변환
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("cqrs");
        MongoCollection<Document> mongo_books = database.getCollection("books");
        // 받은 데이터로 삽입할 데이터를 생성
        Document book = new Document();
        book.append("bid", messageObj.getLong("bid"));
        mongo_books.insertOne(book);
        mongoClient.close();
    }
}
