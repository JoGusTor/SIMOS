c***********************************************************************
C23456789012345678901234567890123456789012345678901234567890123456789012
c***********************************************************************
C***  This subroutine computes the T matrix of the canopy.          ****
c*********************************************************************** 
C
        SUBROUTINE SOLVE_LEAF(LOG_TRUNK,LOG_CROWN)
        save
C
c***********************************************************************
c------------------  variable definitions   ----------------------------
c***********************************************************************
C
C
c***********************************************************************
c------------------  variable declations    ----------------------------
c***********************************************************************
C----------------------------
c%include 'parameters.include'
        INCLUDE 'parameters.include'
C----------------------------
C
        INTEGER I, J, K
C
        REAL T_SIG0_leaf(4,4)
        REAL SIG0_leaf(4,4,N_SCAT_TERMS)
        REAL T_SIG0_dB_leaf(4,4)
        REAL SIG0_dB_leaf(4,4,N_SCAT_TERMS)
        REAL WORK
c
        REAL MG_LEAF,RHO_LEAF,LEAF_DENS,LEAF_DIAM,LEAF_TAU
        REAL THETA, CTHETA, STHETA, DENSITY, CROWN_HGHT, TRUNK_HGHT
        REAL PHASE_PASS(4,4)
        REAL EXP_KAPPA_T_p(4,4), EXP_KAPPA_T_m(4,4)
        REAL EXP_KAPPA_C_p(4,4), EXP_KAPPA_C_m(4,4)
        REAL P_TRUNK(4,4,2), P_TRUNK_LAYER(4,4,2)
        REAL TRUNK_PHASE_MAT_m(4,4),TRUNK_PHASE_MAT_p(4,4)
        REAL P_crown(4,4,4),P_leaf(4,4,4),P_needle(4,4,4)
        REAL P_branch(4,4,4),P_branch_2(4,4,4)
        REAL GRND_REFLECT_MAT(4,4), GRND_BACK_MAT(4,4)
        REAL Rprime0(4,4), RprimePI(4,4), RDUMMAT1(4,4), RDUMMAT2(4,4)
        REAL RDUMMAT(4,4)
        REAL BACKTERMS_leaf(4,4,N_SCAT_TERMS), T_leaf(4,4)
        REAL EXP_CANOPY_p(4,4), EXP_CANOPY_m(4,4)
        real phi_hv_vv,phi_vh_vv,phi_hh_vv
        real phase_diff_terms(3,n_scat_terms), phase_diff_total(3)
        real a_phase(3,n_scat_terms)
C
        character*9 char, char2
C
        COMPLEX Q_T_p(4,4),Q_T_p_INV(4,4),Q_T_m(4,4),Q_T_m_INV(4,4)
        COMPLEX Q_C_p(4,4),Q_C_p_INV(4,4),Q_C_m(4,4),Q_C_m_INV(4,4)
        COMPLEX LAMBDA_T_p(4), LAMBDA_T_m(4)
        COMPLEX LAMBDA_C_p(4), LAMBDA_C_m(4)
        COMPLEX CDUMMAT1(4,4), WORK1, WORK2, WORK3
        COMPLEX A_1(4,4),A_2(4,4),A_3(4,4),A_4(4,4),A_5(4,4),A_6(4,4)
C
        LOGICAL LOG_TRUNK,LOG_CROWN
C
        COMMON /R_LEAF/ MG_LEAF,RHO_LEAF,LEAF_DENS,LEAF_DIAM,LEAF_TAU
        COMMON /RADAR_ANGLE/ THETA, CTHETA, STHETA
        COMMON /R_CANOPY/ DENSITY, CROWN_HGHT, TRUNK_HGHT
        COMMON /R_GROUND_MATS/ GRND_REFLECT_MAT, GRND_BACK_MAT
        COMMON /TRUNK_EXT/ EXP_KAPPA_T_p, EXP_KAPPA_T_m
        COMMON /TRUNK_PHASE/ P_TRUNK, P_TRUNK_LAYER
        COMMON /TRUNK_QS/ Q_T_p, Q_T_p_INV, Q_T_m, Q_T_m_INV
        COMMON /TRUNK_EIGEN/ LAMBDA_T_p, LAMBDA_T_m
        COMMON /CROWN_EXT/ EXP_KAPPA_C_p, EXP_KAPPA_C_m
        COMMON /CROWN_PHASE/ P_CROWN,P_NEEDLE,P_LEAF,P_BRANCH,P_BRANCH_2
        COMMON /CROWN_QS/ Q_C_p, Q_C_p_INV, Q_C_m, Q_C_m_INV
        COMMON /CROWN_EIGEN/ LAMBDA_C_p, LAMBDA_C_m
        COMMON /STOKES_MATS_leaf/ T_leaf, BACKTERMS_leaf
        COMMON /A_MATS/ A_1, A_2, A_3, A_4, A_5, A_6
        COMMON /CANOPY_EXT/ EXP_CANOPY_p, EXP_CANOPY_m
        COMMON /phase_diff_mats/ phase_diff_total, phase_diff_terms
        common /a_phase_diffs/ a_phase
C
C***********************************************************************
C   COMPUTE ONE-WAY EXTINCTION(TRANSMISSIVITY) MATRICES FOR THE CANOPY
C***********************************************************************
C
        CALL MATMULT2(EXP_KAPPA_C_p,EXP_KAPPA_T_p,EXP_CANOPY_p)
        CALL MATMULT2(EXP_KAPPA_T_m,EXP_KAPPA_C_m,EXP_CANOPY_m)
C
C***********************************************************************
C   SET THE A_i MATRICES
C***********************************************************************
C
C--- MATRX A_1 ---  GROUND-CROWN-GROUND (GROUND-DIRECT)
C
      IF(LOG_CROWN)THEN
C
        DO I=1,4
         DO J=1,4
          PHASE_PASS(I,J) = LEAF_DENS*P_leaf(I,J,1)
         ENDDO
        ENDDO
C
        CALL CrMATMULT3(Q_C_m_INV,PHASE_PASS,Q_C_p,CDUMMAT1)
C
        DO 100 J=1,4
         DO 100 I=1,4
            WORK1 = (LAMBDA_C_m(I) + LAMBDA_C_p(J))/CTHETA
            WORK2 = (1.0-EXP(-WORK1*CROWN_HGHT))/WORK1
            A_1(I,J) = WORK2*CDUMMAT1(I,J)
100     CONTINUE
C
C
C--- MATRIX A_2 --- CROWN-GROUND
C
        DO I=1,4
         DO J=1,4
          PHASE_PASS(I,J) = LEAF_DENS*P_leaf(I,J,2)
         ENDDO
        ENDDO
C
        CALL CrMATMULT3(Q_C_m_INV,PHASE_PASS,Q_C_m,CDUMMAT1)
C
        DO 120 J=1,4
         DO 120 I=1,4
            IF((I.EQ.J).OR.
     &         (CABS((LAMBDA_C_m(I)-LAMBDA_C_m(J))/LAMBDA_C_m(I))
     &            .LT.1.0E-5))THEN
                A_2(I,J) = CEXP(-LAMBDA_C_m(I)*CROWN_HGHT/CTHETA)
     &                     *CDUMMAT1(I,J)*CROWN_HGHT
            ELSE
                WORK1 = (-LAMBDA_C_m(I) + LAMBDA_C_m(J))/CTHETA
                WORK2 = CEXP(-LAMBDA_C_m(I)*CROWN_HGHT/CTHETA) - 
     &                  CEXP(-LAMBDA_C_m(J)*CROWN_HGHT/CTHETA) 
                WORK3 = WORK2/WORK1
                A_2(I,J) = WORK3*CDUMMAT1(I,J)
            ENDIF
120     CONTINUE
C
C--- MATRIX A_3 --- GROUND-CROWN
C
        DO I=1,4
         DO J=1,4
          PHASE_PASS(I,J) = LEAF_DENS*P_leaf(I,J,3)
         ENDDO
        ENDDO
C
        CALL CrMATMULT3(Q_C_p_INV,PHASE_PASS,Q_C_p,CDUMMAT1)
C
        DO 140 J=1,4
         DO 140 I=1,4
            IF((I.EQ.J).OR.
     &         (CABS((LAMBDA_C_p(I)-LAMBDA_C_p(J))/LAMBDA_C_p(I))
     &            .LT.1.0E-5))THEN
                A_3(I,J) = CEXP(-LAMBDA_C_p(I)*CROWN_HGHT/CTHETA)
     &                     *CDUMMAT1(I,J)*CROWN_HGHT
            ELSE
                WORK1 = (LAMBDA_C_p(I) - LAMBDA_C_p(J))/CTHETA
                WORK2 = CEXP(-LAMBDA_C_p(J)*CROWN_HGHT/CTHETA)- 
     &                  CEXP(-LAMBDA_C_p(I)*CROWN_HGHT/CTHETA) 
                WORK3 = WORK2/WORK1
                A_3(I,J) = WORK3*CDUMMAT1(I,J)
            ENDIF
140     CONTINUE
C
C--- MATRIX A_4 --- DIRECT CROWN
C
        DO I=1,4
         DO J=1,4
          PHASE_PASS(I,J) = LEAF_DENS*P_leaf(I,J,4)
         ENDDO
        ENDDO
C
        CALL CrMATMULT3(Q_C_p_INV,PHASE_PASS,Q_C_m,CDUMMAT1)
C
        DO 160 J=1,4
         DO 160 I=1,4
            WORK1 = (LAMBDA_C_p(I) + LAMBDA_C_m(J))/CTHETA
            WORK2 = (1.0-CEXP(-WORK1*CROWN_HGHT))/WORK1
            A_4(I,J) = WORK2*CDUMMAT1(I,J)
160     CONTINUE
C
      ELSE
C
        DO 110 J=1,4
         DO 109 I=1,4
            A_1(I,J) = CMPLX(0.0,0.0)
            A_2(I,J) = CMPLX(0.0,0.0)
            A_3(I,J) = CMPLX(0.0,0.0)
            A_4(I,J) = CMPLX(0.0,0.0)
109      CONTINUE
110     CONTINUE
C
      ENDIF
C
      IF(LOG_TRUNK)THEN
C
        DO J=1,4
         DO I=1,4
            TRUNK_PHASE_MAT_p(I,J) = P_TRUNK_LAYER(I,J,1)
            TRUNK_PHASE_MAT_m(I,J) = P_TRUNK_LAYER(I,J,2)
         ENDDO
        ENDDO
C
C--- MATRIX A_5 --- TRUNK-GROUND
C
        CALL CrMATMULT3(Q_T_m_INV,TRUNK_PHASE_MAT_m,Q_T_m,CDUMMAT1)
C
        DO 180 J=1,4
         DO 180 I=1,4
            IF((I.EQ.J).OR.
     &         (CABS((LAMBDA_T_m(I)-LAMBDA_T_m(J))/LAMBDA_T_m(I))
     &            .LT.1.0E-5))THEN
                A_5(I,J) = CEXP(-LAMBDA_T_m(I)*TRUNK_HGHT/CTHETA)
     &                     *CDUMMAT1(I,J)*TRUNK_HGHT
            ELSE
                WORK1 = (-LAMBDA_T_m(I) + LAMBDA_T_m(J))/CTHETA
                WORK2 = CEXP(-LAMBDA_T_m(I)*TRUNK_HGHT/CTHETA) - 
     &                  CEXP(-LAMBDA_T_m(J)*TRUNK_HGHT/CTHETA) 
                WORK3 = WORK2/WORK1
                A_5(I,J) = WORK3*CDUMMAT1(I,J)
            ENDIF
180     CONTINUE
C
C--- MATRIX A_6 ---  GROUND-TRUNK
C
        CALL CrMATMULT3(Q_T_p_INV,TRUNK_PHASE_MAT_p,Q_T_p,CDUMMAT1)
C
        DO 200 J=1,4
         DO 200 I=1,4
            IF((I.EQ.J).OR.
     &         (CABS((LAMBDA_T_p(I)-LAMBDA_T_p(J))/LAMBDA_T_p(I))
     &            .LT.1.0E-5))THEN
                A_6(I,J) = CEXP(-LAMBDA_t_p(I)*TRUNK_HGHT/CTHETA)
     &                     *CDUMMAT1(I,J)*TRUNK_HGHT
            ELSE
                WORK1 = (LAMBDA_t_p(I) - LAMBDA_t_p(J))/CTHETA
                WORK2 = CEXP(-LAMBDA_t_p(J)*TRUNK_HGHT/CTHETA) - 
     &                  CEXP(-LAMBDA_t_p(I)*TRUNK_HGHT/CTHETA) 
                WORK3 = WORK2/WORK1
                A_6(I,J) = WORK3*CDUMMAT1(I,J)
            ENDIF
200     CONTINUE
C
      ELSE
C
        DO 210 J=1,4
         DO 209 I=1,4
            A_5(I,J) = CMPLX(0.0,0.0)
            A_6(I,J) = CMPLX(0.0,0.0)
209      CONTINUE
210     CONTINUE
      ENDIF
C
C***********************************************************************
C   COMPUTE THE Rprime  MATRIX FOR THE SPECULAR GROUND EXTINCTED BY THE
C                   EXTINCTED BY THE TRUNK LAYER
C***********************************************************************
C
          CALL MATMULT3(EXP_KAPPA_T_p, GRND_REFLECT_MAT, EXP_KAPPA_T_m,
     &               Rprime0)
          CALL MATMULT3(EXP_KAPPA_T_m, GRND_REFLECT_MAT, EXP_KAPPA_T_p,
     &               RprimePI)
C
C***********************************************************************
C   COMPUTE THE SEVEN BACKSCATTERING CONTRIBUTIONS
C***********************************************************************
C
C--- TERM 1 ----
C--- GROUND-CROWN-GROUND INTERACTION ---
C
        CALL MATMULT3Cr(Q_C_m,A_1,Q_C_p_INV,RDUMMAT1)
c
        call flip_stokes(RDUMMAT1,RDUMMAT)
c
        call phase_diff(RDUMMAT,phi_hv_vv,phi_vh_vv,phi_hh_vv)
        a_phase(1,1) = phi_hh_vv
        a_phase(2,1) = phi_vh_vv
        a_phase(3,1) = phi_hv_vv
c
        CALL MATMULT3(RprimePI,RDUMMAT1,Rprime0,RDUMMAT2)
        CALL MATMULT3(EXP_KAPPA_C_p,RDUMMAT2,EXP_KAPPA_C_m,RDUMMAT1)
c
        call flip_stokes(RDUMMAT1,RDUMMAT)
c
        CALL SET_TERM(RDUMMAT,BACKTERMS_leaf,CTHETA,1)
c
        call phase_diff(RDUMMAT,phi_hv_vv,phi_vh_vv,phi_hh_vv)
        phase_diff_terms(1,1) = phi_hh_vv
        phase_diff_terms(2,1) = phi_vh_vv
        phase_diff_terms(3,1) = phi_hv_vv
C
C--- TERM 2 ----
C--- GROUND-CROWN INTERACTION ---
C
        CALL MATMULT3Cr(Q_C_p,A_3,Q_C_p_INV,RDUMMAT1)
c
        call phase_diff(RDUMMAT1,phi_hv_vv,phi_vh_vv,phi_hh_vv)
        a_phase(1,2) = phi_hh_vv
        a_phase(2,2) = phi_vh_vv
        a_phase(3,2) = phi_hv_vv
c
        CALL MATMULT3(RDUMMAT1,RprimePI,EXP_KAPPA_C_m,RDUMMAT2)
        CALL SET_TERM(RDUMMAT2,BACKTERMS_leaf,CTHETA,2)
c
        call phase_diff(RDUMMAT2,phi_hv_vv,phi_vh_vv,phi_hh_vv)
        phase_diff_terms(1,2) = phi_hh_vv
        phase_diff_terms(2,2) = phi_vh_vv
        phase_diff_terms(3,2) = phi_hv_vv
C
C--- TERM 3 ----
C--- CROWN-GROUND INTERACTION ---
C
        CALL MATMULT3Cr(Q_C_m,A_2,Q_C_m_INV,RDUMMAT1)
c
        call phase_diff(RDUMMAT1,phi_hv_vv,phi_vh_vv,phi_hh_vv)
        a_phase(1,3) = phi_hh_vv
        a_phase(2,3) = phi_vh_vv
        a_phase(3,3) = phi_hv_vv
c
        CALL MATMULT3(EXP_KAPPA_C_p,Rprime0,RDUMMAT1,RDUMMAT2)
        CALL SET_TERM(RDUMMAT2,BACKTERMS_leaf,CTHETA,3)
c
        call phase_diff(RDUMMAT2,phi_hv_vv,phi_vh_vv,phi_hh_vv)
        phase_diff_terms(1,3) = phi_hh_vv
        phase_diff_terms(2,3) = phi_vh_vv
        phase_diff_terms(3,3) = phi_hv_vv
C
C--- TERM 4 ----
C--- DIRECT CROWN BACKSCATTER ---
C
        CALL MATMULT3Cr(Q_C_p,A_4,Q_C_m_INV,RDUMMAT1)
c
        call flip_stokes(RDUMMAT1,RDUMMAT)
c
        CALL SET_TERM(RDUMMAT,BACKTERMS_leaf,CTHETA,4)
c
        call phase_diff(RDUMMAT,phi_hv_vv,phi_vh_vv,phi_hh_vv)
        phase_diff_terms(1,4) = phi_hh_vv
        phase_diff_terms(2,4) = phi_vh_vv
        phase_diff_terms(3,4) = phi_hv_vv
        a_phase(1,4) = phi_hh_vv
        a_phase(2,4) = phi_vh_vv
        a_phase(3,4) = phi_hv_vv
C
C--- TERM 5 ----
C--- TRUNK-GROUND INTERACTION ---
C
        CALL MATMULT3Cr(Q_T_m,A_5,Q_T_m_INV,RDUMMAT1)
c
        call phase_diff(RDUMMAT1,phi_hv_vv,phi_vh_vv,phi_hh_vv)
        a_phase(1,5) = phi_hh_vv
        a_phase(2,5) = phi_vh_vv
        a_phase(3,5) = phi_hv_vv
c
        CALL MATMULT3(GRND_REFLECT_MAT,RDUMMAT1,EXP_KAPPA_C_m,RDUMMAT2)
        CALL MATMULT3(EXP_KAPPA_C_p,EXP_KAPPA_T_p,RDUMMAT2,RDUMMAT1)
        CALL SET_TERM(RDUMMAT1,BACKTERMS_leaf,CTHETA,5)
c
        call phase_diff(RDUMMAT1,phi_hv_vv,phi_vh_vv,phi_hh_vv)
        phase_diff_terms(1,5) = phi_hh_vv
        phase_diff_terms(2,5) = phi_vh_vv
        phase_diff_terms(3,5) = phi_hv_vv
C
C--- TERM 6 ----
C--- GROUND-TRUNK INTERACTION ---
C
        CALL MATMULT3Cr(Q_T_p,A_6,Q_T_p_INV,RDUMMAT1)
c
        call phase_diff(RDUMMAT1,phi_hv_vv,phi_vh_vv,phi_hh_vv)
        a_phase(1,6) = phi_hh_vv
        a_phase(2,6) = phi_vh_vv
        a_phase(3,6) = phi_hv_vv
c
        CALL MATMULT3(EXP_KAPPA_C_p,RDUMMAT1,GRND_REFLECT_MAT,RDUMMAT2)
        CALL MATMULT3(RDUMMAT2,EXP_KAPPA_T_m,EXP_KAPPA_C_m,RDUMMAT1)
        CALL SET_TERM(RDUMMAT1,BACKTERMS_leaf,CTHETA,6)
c
        call phase_diff(RDUMMAT1,phi_hv_vv,phi_vh_vv,phi_hh_vv)
        phase_diff_terms(1,6) = phi_hh_vv
        phase_diff_terms(2,6) = phi_vh_vv
        phase_diff_terms(3,6) = phi_hv_vv
C
C--- TERM 7 ----
C--- DIRECT GROUND BACKSCATTER ---  NOTE: A(7) = GRND_BACK_MAT
C

        call phase_diff(GRND_BACK_MAT,phi_hv_vv,phi_vh_vv,phi_hh_vv)
        a_phase(1,7) = phi_hh_vv
        a_phase(2,7) = phi_vh_vv
        a_phase(3,7) = phi_hv_vv
c
       CALL MATMULT3(EXP_KAPPA_T_p,GRND_BACK_MAT,EXP_KAPPA_T_m,RDUMMAT1)
       CALL MATMULT3(EXP_KAPPA_C_p,RDUMMAT1,EXP_KAPPA_C_m,RDUMMAT2)
c       CALL SET_TERM(RDUMMAT2,BACKTERMS_leaf,1.0,7)
       CALL SET_TERM(RDUMMAT2,BACKTERMS_leaf,ctheta,7)
c
        call phase_diff(RDUMMAT2,phi_hv_vv,phi_vh_vv,phi_hh_vv)
        phase_diff_terms(1,7) = phi_hh_vv
        phase_diff_terms(2,7) = phi_vh_vv
        phase_diff_terms(3,7) = phi_hv_vv
C
C***********************************************************************
C   COMPUTE THE TOTAL BACKSCATTERING TRANSFORMATION MATRIX, T
C***********************************************************************
C
        DO 490 I=1,4
         DO 490 J=1,4
            T_leaf(J,I) = 0.0
490     CONTINUE
C
        DO 500 K=1,7
         DO 500 I=1,4
          DO 500 J=1,4
            T_leaf(J,I) = T_leaf(J,I) + BACKTERMS_leaf(J,I,K)
500     CONTINUE
c
        call phase_diff(T_leaf,phi_hv_vv,phi_vh_vv,phi_hh_vv)
        phase_diff_total(1) = phi_hh_vv
        phase_diff_total(2) = phi_vh_vv
        phase_diff_total(3) = phi_hv_vv
c
C***********************************************************************
C***********************************************************************
C------ Compute Sigma 0's ----------------------------------------------
C***********************************************************************
C
        WORK = 4.0*3.14159*CTHETA
C
        DO 620 K=1, N_SCAT_TERMS
          DO 610 I=1,2
            DO 600 J=1,2
                SIG0_leaf(J,I,K) = WORK*BACKTERMS_leaf(J,I,K)
                IF(SIG0_leaf(J,I,K) .GT. 0.0)THEN
                SIG0_dB_leaf(J,I,K) = 10.0*ALOG10(SIG0_leaf(J,I,K))
                ELSE
                    SIG0_dB_leaf(J,I,K) = -999.99
                ENDIF
600         CONTINUE
610       CONTINUE
620     CONTINUE
C
        DO 640 I=1,2
            DO 630 J=1,2
                T_SIG0_leaf(J,I) = WORK*T_leaf(J,I)
                IF(T_SIG0_leaf(J,I) .GT. 0.0)THEN
                T_SIG0_dB_leaf(J,I) = 10.0*ALOG10(T_SIG0_leaf(J,I))
                ELSE
                    T_SIG0_dB_leaf(J,I) = -999.99
                ENDIF
630         CONTINUE
640     CONTINUE
C

       open(unit=8,file='../output_dir/forest_sigma_leaf_like.out')
       open(unit=7,file='../output_dir/forest_sigma_leaf_cross.out')
C
       char='    '
       char2='    ' 
           write(8,900) char,char2
            write(7,901) char,char2
C
900     format(' **** ','Backscattering Cross Section - Sigma0 (dB)',
     &      ' ****',/,
     &      1x,a9,5x,
     &      'Total Backscatter', 3x,'Gnd-Crown-Gnd',7x,
     &      'Crown-Ground',      7x,'Ground-Crown', 7x,
     &      'Direct Crown',      7x,'Trunk-Ground', 7x,
     &      'Ground-Trunk',      7x,'Direct Ground',/,
     &      1x,a9,2x,8(6x,'VV',6x,'HH',3x))
c
901     format(' **** ','Backscattering Cross Section - Sigma0 (dB)',
     &      ' ****',/,
     &      1x,a9,5x,
     &      'Total Backscatter', 3x,'Gnd-Crown-Gnd',7x,
     &      'Crown-Ground',      7x,'Ground-Crown', 7x,
     &      'Direct Crown',      7x,'Trunk-Ground', 7x,
     &      'Ground-Trunk',      7x,'Direct Ground',/,
     &      1x,a9,2x,8(6x,'VH',6x,'HV',3x))
C
     
       WRITE(8,800) 
     &                T_SIG0_dB_leaf(1,1),T_SIG0_dB_leaf(2,2),
     &               (SIG0_dB_leaf(1,1,K), 
     &               SIG0_dB_leaf(2,2,K),K=1,N_SCAT_TERMS)
C
        WRITE(7,800) 
     &                T_SIG0_dB_leaf(1,2),T_SIG0_dB_leaf(2,1),
     &               (SIG0_dB_leaf(1,2,K), 
     &                SIG0_dB_leaf(2,1,K),K=1,N_SCAT_TERMS)

800     format(8(3X,F7.2,2X,F7.2))
      
      close(unit=8)
      close(unit=7)

C
        RETURN
        END
CC***********************************************************************
Cc***********************************************************************
CC23456789012345678901234567890123456789012345678901234567890123456789012
Cc***********************************************************************
CC***  This subroutine sets the appropriate term of the backscatter  ****
CC***              terms matrix 'BACKTERMS'.                         ****
Cc*********************************************************************** 
CC
C        SUBROUTINE SET_TERM(INPUT,BACKTERMS_leaf,MU0,K)
C        save
CC
Cc***********************************************************************
Cc------------------  variable definitions   ----------------------------
Cc***********************************************************************
Cc------------------  variable declations    ----------------------------
Cc***********************************************************************
CC----------------------------
Cc%include 'parameters.include'
C        INCLUDE 'parameters.include'
CC----------------------------
CC
C        INTEGER I,J,K
C        REAL MU0, BACKTERMS_leaf(4,4,N_SCAT_TERMS),INPUT(4,4)
C
CC***********************************************************************
CC
C        DO 10 I=1,4
C          DO 10 J=1,4
C            BACKTERMS_leaf(J,I,K) = INPUT(J,I)/MU0
C10      CONTINUE
CC
C        RETURN
C        END
CC
CC***********************************************************************
Cc***********************************************************************
Cc23456789012345678901234567890123456789012345678901234567890123456789012
Cc***********************************************************************
Cc   Subroutine to compute phase differences (relative to VV)
Cc   given a Stoke's matrix.
Cc   Routine written 3-18-89 by Kyle McDonald
Cc***********************************************************************
Cc
c        subroutine phase_diff(stokes,phi_hv_vv,phi_vh_vv,phi_hh_vv)
c        save
c
c***********************************************************************
c
c        real stokes(4,4), phi_hv_vv, phi_vh_vv, phi_hh_vv
c        real x, y, pi
c
c***********************************************************************
c
c        pi = 3.141592654
c
c        if((stokes(1,4).ne.0.0).or.(stokes(1,3).ne.0.0))then
c            phi_vh_vv = atan2(stokes(1,4),stokes(1,3))
c        else
c            phi_vh_vv = 0.0
c        endif  
c
c        if((stokes(4,1).ne.0.0).or.(stokes(3,1).ne.0.0))then
c            phi_hv_vv = atan2(-stokes(4,1),stokes(3,1))
c        else
c            phi_hv_vv = 0.0
c        endif 
c
c        x = -2.0*stokes(1,1)*stokes(4,3) + stokes(3,1)*stokes(1,4) +
c     &        stokes(1,3)*stokes(4,1)
c        y = 2.0*stokes(1,1)*stokes(3,3) - stokes(3,1)*stokes(1,3) +
c     &        stokes(1,4)*stokes(4,1)
cc
c        phi_hh_vv = atan2(x,y)
cc
c***********************************************************************
c
c        phi_hh_vv = phi_hh_vv*(180./pi)
c        phi_vh_vv = phi_vh_vv*(180./pi)
c        phi_hv_vv = phi_hv_vv*(180./pi)
c
c        return
c        end
c
c***********************************************************************
c***********************************************************************
c23456789012345678901234567890123456789012345678901234567890123456789012
c***********************************************************************
c   Subroutine to flip a stokes matrix so that the h-polarization
c   vector in flipped
c   Routine written 3-21-89 by Kyle McDonald
c***********************************************************************
c
c        subroutine flip_stokes(stokes_in,stokes_out)
c        save
cc
c        real stokes_in(4,4), stokes_out(4,4)
c
c        stokes_out(1,1) =  stokes_in(1,1)
c        stokes_out(1,2) =  stokes_in(1,2)
c        stokes_out(1,3) =  stokes_in(1,3)
c        stokes_out(1,4) =  stokes_in(1,4)
c        stokes_out(2,1) =  stokes_in(2,1)
c        stokes_out(2,2) =  stokes_in(2,2)
c        stokes_out(2,3) =  stokes_in(2,3)
c        stokes_out(2,4) =  stokes_in(2,4)
c        stokes_out(3,1) = -stokes_in(3,1)
c        stokes_out(3,2) = -stokes_in(3,2)
c        stokes_out(3,3) = -stokes_in(3,3)
c        stokes_out(3,4) = -stokes_in(3,4)
c        stokes_out(4,1) = -stokes_in(4,1)
c        stokes_out(4,2) = -stokes_in(4,2)
c        stokes_out(4,3) = -stokes_in(4,3)
c        stokes_out(4,4) = -stokes_in(4,4)
cc
c        return
c        end
cc
cc***********************************************************************
c
