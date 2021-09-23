package za.ac.nwu.ac.domain.persistence;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import  java.time.LocalDate;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "ACCOUNT_TX", schema = "CMPG323")
public class AccountTransaction implements Serializable {

    @Id
    @SequenceGenerator(name = "VIT_RSA_GENERIC_SEQ", sequenceName = "CMPG323.VIT_RSA_GENERIC_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "VIT_RSA_GENERIC_SEQ")

    @Column(name = "TX_ID")
    private  Long transactionId;

    @Column(name = "ACCOUNT_TYPE_ID")
    private  Long accountId;

    @Column(name = "MEMBER_ID")
    private  Long memberId;

    @Column(name = "AMOUNT")
    private  Long amount;

    @Column(name = "TX_DATE")
    private  LocalDate transactionDate;
}
