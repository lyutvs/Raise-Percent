package kr.hs.entrydsm.raisepercent.domain.document.service;

import kr.hs.entrydsm.raisepercent.domain.document.domain.StayDocument;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.PublicDocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.StayDocumentRepository;
import kr.hs.entrydsm.raisepercent.global.exception.DocumentNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ApproveStayDocumentServiceTest {

    private static final StayDocumentRepository stayDocumentRepository = mock(StayDocumentRepository.class);

    private static final PublicDocumentRepository publicDocumentRepository = mock(PublicDocumentRepository.class);

    private static final ApproveStayDocumentService service = new ApproveStayDocumentService(stayDocumentRepository, publicDocumentRepository);

    @Test
    void 대기문서_승인_성공() {
        List<StayDocument> stayDocumentList = List.of(StayDocument.builder().build());

        given(stayDocumentRepository.findByIdDocumentId(any()))
                .willReturn(stayDocumentList);

        service.execute("123e4567-e89b-12d3-a456-426614174000");

        verify(publicDocumentRepository, times(1)).save(any());

        verify(stayDocumentRepository, times(1)).deleteAll(any());
    }

    @Test
    void 문서_존재하지_않음_예외() {
        given(stayDocumentRepository.findByIdDocumentId(any()))
                .willReturn(new ArrayList<>());

        assertThrows(DocumentNotFoundException.class, () -> service.execute("123e4567-e89b-12d3-a456-426614174000"));
    }

}