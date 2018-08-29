package com.iafnstudios.spring5webapp.bootstrap;

import com.iafnstudios.spring5webapp.model.Author;
import com.iafnstudios.spring5webapp.model.Book;
import com.iafnstudios.spring5webapp.model.Publisher;
import com.iafnstudios.spring5webapp.repositories.AuthorRepository;
import com.iafnstudios.spring5webapp.repositories.BookRepository;
import com.iafnstudios.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/*Application Listener can generically declare the event type that it is interested in.
 When registered with a Spring Application Context, events will be filtered accordingly,
 with the listener getting invoked for matching event objects only.

Application events are available since the very beginning of the Spring framework as a
mean for loosely coupled components to exchange information.*/


@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }


    private void initData(){
        Author eric = new Author("Eric","Avans");
        Publisher harperCollins = new Publisher("Harper Collins","USA");
        Book ddd = new Book("Domain Driven Design","123",harperCollins);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        publisherRepository.save(harperCollins);
        authorRepository.save(eric);
        bookRepository.save(ddd);


        Author rod = new Author("Rod","Johnson");
        Publisher worx = new Publisher("Worx","England");
        Book noEJB = new Book("J2EE Development without EJB","456",worx);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        publisherRepository.save(worx);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }


}
