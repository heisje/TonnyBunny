package com.tonnybunny.domain.user.service;

import com.tonnybunny.domain.user.dto.CertificateRequestDto;
import com.tonnybunny.domain.user.dto.HelperInfoRequestDto;
import com.tonnybunny.domain.user.entity.CertificateEntity;
import com.tonnybunny.domain.user.entity.PossibleLanguageEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class HelperInfoService {
    /**
     * 유저가 가진 자격증 목록 조회
     * @param userSeq : 대상 유저 seq
     * @return 자격증 목록 조회
     */
    public List<CertificateEntity> getCertificateList(int userSeq){
        // TODO : 로직 구현
        return new ArrayList<>();
    }

    /**
     * 대상 유저에 속하는 자격증을 추가, 한번에 여러 자격증을 등록
     * @param userSeq : 대상 유저 seq
     * @param certificateRequestDtoList : 등록할 자격증 목록
     * @return 등록한 자격증 entity의 seq 목록
     */
    public List<Long> createCertificateList(int userSeq, List<CertificateRequestDto> certificateRequestDtoList){
        // TODO : 로직 구현

        return new ArrayList<>();
    }

    /**
     * 한번에 여러 자격증 정보를 삭제
     * @param certificateSeqList : 삭제할 자격증 seq 목록
     * @return 삭제 성공 여부
     */
    public Boolean deleteCertificateList(List<Long> certificateSeqList){
        // TODO : 로직 구현
        return true;
    }

    /**
     * 유저가 등록했던 헬퍼 소개서 이미지 목록을 조회
     * @param userSeq : 대상 유저 seq // FIXME : 테이블의 관계는 helperInfoSeq와 연결되어 있으므로, 후에 편한 방식으로 수정하기.
     * @return 헬퍼 소개서 이미지 파일 목록
     */
    public List<MultipartFile> getHelperInfoImageList(Long userSeq){
        // TODO : 로직 구현

        return new ArrayList<>();
    }

    /**
     * 헬퍼 소개서 이미지를 등록, 한번에 여러 이미지 등록 가능
     * @param helperInfoSeq : 대상 헬퍼의 정보 seq
     * @param helperInfoImage : 이미지 파일
     * @return 추가한 헬퍼 소개서 이미지들의 seq 목록
     */
    public List<Long> createHelperInfoImageList(int helperInfoSeq, MultipartFile helperInfoImage){
        // TODO : 로직 구현
        return new ArrayList<>();
    }

    /**
     * 헬퍼 소개서 이미지를 삭제, 한번에 여러 이미지 삭제 가능
     * @param helperInfoImageSeqList : 삭제할 헬퍼 소개서 이미지의 seq 목록
     * @return 삭제 성공 여부
     */
    public Boolean deleteHelperInfoImageList(List<Long> helperInfoImageSeqList){
        // TODO : 로직 구현
        return true;
    }

    /**
     * 대상 유저의 가능 언어 목록을 조회
     * @param userSeq : 대상 유저 seq
     * @return 가능 언어 목록 // FIXME : String(공통코드) or Entity 중 편한 걸로 수정하기.
     */
    public List<PossibleLanguageEntity> getPossibleLangList(Long userSeq){
        // TODO : 로직 구현

        return new ArrayList<>();
    }

    /**
     * 대상 유저의 가능 언어를 추가, 한번에 여러 언어 추가 가능
     * @param userSeq : 대상 유저 seq
     * @param possibleLangCodeList : 추가할 언어의 공통코드 목록
     * @return 추가한 가능 언어 Entity의 seq 목록
     */
    public List<Long> createPossibleLangList(Long userSeq, List<String> possibleLangCodeList){
        // TODO : 로직 구현

        return new ArrayList<>();
    }

    /**
     * 가능 언어를 삭제, 한번에 여러 언어 삭제 가능
     * @param possibleLangSeqList : 삭제할 가능 언어 Entity의 seq 목록
     * @return 삭제 성공 여부
     */
    public Boolean deletePossibleLangList(List<Long> possibleLangSeqList){
        // TODO : 로직 구현

        return true;
    }

    /**
     * 헬퍼 정보를 수정
     * @param helperInfoSeq : 수정될 헬퍼 정보 entity의 seq
     * @param helperInfoRequestDto : 수정할 헬퍼 정보
     * @return 수정한 Entity의 seq
     */
    public Long modifyHelperInfo(int helperInfoSeq, HelperInfoRequestDto helperInfoRequestDto){
        // TODO : 로직 구현

        return 1L;
    }
}
