package com.manish.moodle.service;

import com.manish.moodle.entity.Book;
import com.manish.moodle.entity.Borrowing;
import com.manish.moodle.entity.Member;
import com.manish.moodle.repository.BookRepository;
import com.manish.moodle.repository.BorrowingRepository;
import com.manish.moodle.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BorrowingService {
    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;
    @Transactional
    public Borrowing borrowBook(Long bookId, Long memberId ){
        Optional<Book> bookop= bookRepository.findById(bookId);
        Optional<Member> memberop= memberRepository.findById(memberId);

        if (bookop.isPresent() && memberop.isPresent()){
            Book book= bookop.get();
            Member member= memberop.get();

            if(book.isAvailable()){
                book.setAvailable(false);

                Borrowing borrowing= new Borrowing();

                borrowing.setBook(book);
                borrowing.setMember(member);
                borrowing.setBorrow_date(LocalDate.now());

                return borrowingRepository.save(borrowing);

            }
            else {
                throw new RuntimeException("Book is not available at this time.");
            }

        }
        else{
            throw new RuntimeException("Book or member not found.");
        }
    }
    @Transactional
    public Borrowing returnBook(Long borrowingId){
        Optional<Borrowing> borrowingop = borrowingRepository.findById(borrowingId);

        if(borrowingop.isPresent()){

            Borrowing borrowing= borrowingop.get();
            Book book= borrowing.getBook();
            book.setAvailable(true);

            borrowing.setReturn_date(LocalDate.now());

            bookRepository.save(book);

            return borrowingRepository.save(borrowing);
        }
        else{
            throw new RuntimeException("No borrowing record for this book is present.");
        }
    }


}
