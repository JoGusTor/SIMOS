C***********************************************************************
C23456789012345678901234567890123456789012345678901234567890123456789012
C***********************************************************************
C*** This subroutine sets up parameter values for looping through    ***
C*** MIMICS program.                                                 ***
C***    Calling routine:      main MIMICS program                    ***
C***    Called subroutines:   SET_LOOP_COUNTER                       ***
C***                          STEP                                   ***
C***                          RESET                                  ***
C*********************************************************************** 
C
        SUBROUTINE START_LOOP(WRITE_HEADER,COMPUTE,LOG_CROWN)
        save
C
C***********************************************************************
C------------------  variable definitions   ----------------------------
C***********************************************************************
C
C
C***********************************************************************
C------------------  variable declarations -----------------------------
C***********************************************************************
C----------------------------
c%include 'parameters.include'
        include 'parameters.include'
C----------------------------
C
        INTEGER I, J, K, KK
        INTEGER PARAM_NUM(N_VARIABLES), INEST(N_VARIABLES)
        INTEGER LOOP_NUM(N_VARIABLES), LOOP_COUNT(N_VARIABLES)
C
        REAL FREQ_HERTZ,WAVELENGTH,k0,THETA,CTHETA,STHETA
C
        LOGICAL OPEN, SWITCH, WRITE_HEADER, COMPUTE
        LOGICAL STEP_VARIABLE(N_VARIABLES)
        LOGICAL CALL_SUB(N_CALLS, N_SUB_CALLS)
        LOGICAL STEP_THIS_TIME(N_VARIABLES)
        LOGICAL LOG_CROWN
C
        COMMON /I_NEST/ PARAM_NUM, INEST
        COMMON /I_COUNT/ LOOP_NUM, LOOP_COUNT
        COMMON /L_FLAGS/ STEP_VARIABLE, STEP_THIS_TIME, CALL_SUB, OPEN
        COMMON /RADAR_FREQ/ FREQ_HERTZ, WAVELENGTH, k0
        COMMON /RADAR_ANGLE/ THETA, CTHETA, STHETA
C
        REAL THETA_DEGREES, FREQ_GHZ
        COMMON /R_SENSOR/ THETA_DEGREES, FREQ_GHZ
C
        LOGICAL LOG_CONSTITUENT(N_CONSTITUENTS)
        LOGICAL LOG_EPS_TABLE(N_CONSTITUENTS)
        LOGICAL LOG_DRY_DENSITY(N_CONSTITUENTS) 
        LOGICAL LOG_PDF_TYPE(N_CONSTITUENTS,N_CONST_VARY)
        LOGICAL SOIL_SURFACE,WATER_SURFACE,SNOW_SURFACE,ICE_SURFACE
        LOGICAL LOG_HIST(N_CONSTITUENTS)
C
        COMMON /L_CONFIGURE/ LOG_CONSTITUENT, LOG_EPS_TABLE,
     &                       LOG_DRY_DENSITY, LOG_PDF_TYPE, LOG_HIST
        COMMON /L_SURFACE_TYPE/ SOIL_SURFACE,WATER_SURFACE,
     &                          SNOW_SURFACE,ICE_SURFACE

C
        LOGICAL CHANGE_VAR(N_VARIABLES),STEP_LAST_TIME(N_VARIABLES)
        COMMON /L_FLAG_SWAP/ CHANGE_VAR,STEP_LAST_TIME
C
        LOGICAL STEP_TOGETHER, STEP_THIS_TOGETHER(N_VARIABLES)
        LOGICAL THIS_RESET(N_VARIABLES)
C
C----------------------------
c%include 'constants.include'
        include 'constants.include'
C----------------------------
C***********************************************************************
C
      IF(.NOT.(OPEN))THEN
C
C***********************************************************************
C   FIRST TIME THROUGH THE PROGRAM
C***********************************************************************

      DO 5 I=1,N_VARIABLES
          STEP_THIS_TIME(I) = .FALSE.
          STEP_LAST_TIME(I) = .FALSE.
          CHANGE_VAR(I) = .FALSE.
5     CONTINUE
C
C
        CALL SET_LOOP_COUNTER(LOOP_NUM)
C
C***********************************************************************
c   check to see if variables are stepped together in unison
C
C***********************************************************************
c
        DO 10 J=1,N_VARIABLES
            DO 9 I=1,N_VARIABLES
                IF((INEST(I).EQ.INEST(J)).AND.(I.NE.J))THEN
                    STEP_TOGETHER = .TRUE.
                    STEP_THIS_TOGETHER(J) = .TRUE.
C                    STEP_VARIABLE(J) = .TRUE.
C                    STEP_THIS_TIME(J) = .TRUE.
C                    STEP_LAST_TIME(J) = .TRUE.
                ENDIF
9           CONTINUE
10       CONTINUE
C
C***********************************************************************
C
C        IF(STEP_TOGETHER)THEN
C            WRITE_HEADER = .TRUE.
C        ELSE
C
C-- SET VARIABLE COUNTERS ---
C
            J = 0
            DO 50 I=1,N_VARIABLES
             DO 40 K=1,N_VARIABLES
                IF(INEST(K).EQ.I)THEN
                    IF(LOOP_NUM(K).GT.1)THEN
                        STEP_VARIABLE(K) = .TRUE.
                        IF(J.EQ.0)THEN
                            WRITE_HEADER = .TRUE.
                            J = K
                            STEP_THIS_TIME(K) = .TRUE.
                            STEP_LAST_TIME(K) = .TRUE.
                            IF(STEP_THIS_TOGETHER(K))THEN
                                DO 35 KK=1,N_VARIABLES
                                    IF(STEP_THIS_TOGETHER(KK))THEN
                                        STEP_THIS_TIME(KK) = .TRUE.
                                        STEP_LAST_TIME(KK) = .TRUE.
                                    ENDIF
35                              CONTINUE
                            ENDIF
                        ENDIF
                    ENDIF
                ENDIF
40           CONTINUE
50          CONTINUE
C        ENDIF
C
C-- INITAILIZE CALL_SUB FLAGS --
C
        DO 55 I=1,N_SUB_CALLS
         DO 54 J=1,N_CALLS
          CALL_SUB(j,i) = .TRUE.
54       CONTINUE
55      CONTINUE
C
        IF(.NOT.WATER_SURFACE) CALL_SUB(1,2) = .FALSE.

        IF(.NOT.SOIL_SURFACE)   CALL_SUB(1,3) = .FALSE.

        IF(.NOT.SNOW_SURFACE) CALL_SUB(1,9) = .FALSE.

        IF(.NOT.LOG_CONSTITUENT(5)) THEN
            CALL_SUB(1,4) = .FALSE.
            CALL_SUB(4,4) = .FALSE.
        ENDIF

        IF(.NOT.LOG_CONSTITUENT(4)) THEN
            CALL_SUB(1,5) = .FALSE.
            CALL_SUB(4,3) = .FALSE.
        ENDIF

        IF(.NOT.LOG_CONSTITUENT(1)) THEN
            CALL_SUB(1,6) = .FALSE.
            CALL_SUB(3,2) = .FALSE.
        ENDIF

        IF(.NOT.LOG_CONSTITUENT(2)) THEN
            CALL_SUB(1,7) = .FALSE.
            CALL_SUB(4,2) = .FALSE.
        ENDIF

        IF(.NOT.LOG_CONSTITUENT(3)) THEN
            CALL_SUB(1,8) = .FALSE.
            CALL_SUB(4,5) = .FALSE.
        ENDIF

        IF(.NOT.(LOG_CONSTITUENT(2).OR.LOG_CONSTITUENT(3).OR.
     &           LOG_CONSTITUENT(4).OR.LOG_CONSTITUENT(5)))THEN
            LOG_CROWN = .FALSE.
        ELSE
            LOG_CROWN = .TRUE.
        ENDIF

        IF(LOG_EPS_TABLE(1)) CALL_SUB(1,6) = .FALSE.
        IF(LOG_EPS_TABLE(2)) CALL_SUB(1,7) = .FALSE.
        IF(LOG_EPS_TABLE(3)) CALL_SUB(1,8) = .FALSE.
        IF(LOG_EPS_TABLE(4)) CALL_SUB(1,5) = .FALSE.
        IF(LOG_EPS_TABLE(5)) CALL_SUB(1,4) = .FALSE.
        IF(LOG_EPS_TABLE(6)) THEN
            CALL_SUB(1,2) = .FALSE.
            CALL_SUB(1,3) = .FALSE.
        ENDIF

        IF(.NOT.(CALL_SUB(1,2).OR.CALL_SUB(1,3).OR.CALL_SUB(1,4).OR.
     &           CALL_SUB(1,5).OR.CALL_SUB(1,6).OR.
     &           CALL_SUB(1,7).OR.CALL_SUB(1,8)))THEN
           CALL_SUB(1,1) = .FALSE.
        ENDIF
C
c        print*,' STEP_TOGETHER = ', STEP_TOGETHER
c        do j=1,n_variables
c        print*,'STEP_THIS_TOGETHER(J),STEP_VARIABLE(J),STEP_THIS_TIME(J)
c     &,STEP_LAST_TIME(J) = ',
c     & STEP_THIS_TOGETHER(J),STEP_VARIABLE(J),STEP_THIS_TIME(J)
c     & ,STEP_LAST_TIME(J)
c        enddo
        call logo
c
      ELSE
C
C***********************************************************************
C         NOT THE FIRST TIME THROUGH
C   CHECK FOR LOOPING IN PROPER NESTING ORDER 
C***********************************************************************
C
C        IF(STEP_TOGETHER)THEN
C            WRITE_HEADER = .FALSE.
C            DO 58 I=1,N_VARIABLES
C                IF(STEP_VARIABLE(I))THEN
C                    CHANGE_VAR(I) = .TRUE.
C                    LOOP_COUNT(I) = LOOP_COUNT(I) + 1
C                    CALL STEP(I, LOOP_COUNT(I))
C                ENDIF
C58          CONTINUE
CC
C        ELSE
C
            DO 60 I=1,N_VARIABLES
                STEP_LAST_TIME(I) = STEP_THIS_TIME(I)
                THIS_RESET(I) = .FALSE.
60          CONTINUE

            DO 65 I=1,N_VARIABLES
                STEP_THIS_TIME(I) = .FALSE.
                CHANGE_VAR(I) = .FALSE.
65          CONTINUE

C-- SET VARIABLE COUNTERS ---
C
            SWITCH = .FALSE.
            DO 100 I=1,N_VARIABLES
             DO 90 J=1,N_VARIABLES
                IF(INEST(J).EQ.I)THEN
                    IF(STEP_VARIABLE(J).AND.(.NOT.THIS_RESET(J)))THEN
                        IF(LOOP_COUNT(J).LT.LOOP_NUM(J))THEN
                            LOOP_COUNT(J) = LOOP_COUNT(J) + 1
                            CALL STEP(J, LOOP_COUNT(J))
                            STEP_THIS_TIME(J) = .TRUE.
                            CHANGE_VAR(J) = .TRUE.
                            IF(.NOT.SWITCH) WRITE_HEADER = .FALSE.
C
                            IF(STEP_THIS_TOGETHER(J))THEN
        print*,' stepping: j = ', j
                             DO 80 K=1,N_VARIABLES
                              IF((J.NE.K).AND.
     &                           (STEP_THIS_TOGETHER(K)).AND.
     &                           (INEST(J).EQ.INEST(K)))THEN
        print*,' k = ', k
                                    LOOP_COUNT(K) = LOOP_COUNT(K) + 1
                                    CALL STEP(K, LOOP_COUNT(K))
                                    STEP_THIS_TIME(K) = .TRUE.
                                    CHANGE_VAR(K) = .TRUE.
                              ENDIF
80                           CONTINUE
                             GO TO 200
                            ELSE
                                GO TO 200
                            ENDIF
C
                        ELSE
                            CALL RESET(J)
                            LOOP_COUNT(J) = 1
                            CHANGE_VAR(J) = .TRUE.
                            WRITE_HEADER = .TRUE.
                            SWITCH = .TRUE.
                            THIS_RESET(J) = .TRUE.
C
                            IF(STEP_THIS_TOGETHER(J))THEN
        print*,' resetting: j = ', j
                             DO 85 K=1,N_VARIABLES
                              IF((J.NE.K).AND.
     &                           (STEP_THIS_TOGETHER(K)).AND.
     &                           (INEST(J).EQ.INEST(K)))THEN
        print*,' k = ', k
                                    CALL RESET(K)
                                    LOOP_COUNT(K) = 1
                                    CHANGE_VAR(K) = .TRUE.
                                    THIS_RESET(K) = .TRUE.
                              ENDIF
85                           CONTINUE
                            ENDIF
                        ENDIF
                    ENDIF
                ENDIF
90           CONTINUE
100         CONTINUE
C
200        CONTINUE
CC
C        ENDIF
CC
C-- INITAILIZE CALL_SUB FLAGS --
C
        DO 255 I=1,N_SUB_CALLS
         DO 254 J=1,N_CALLS
          CALL_SUB(j,i) = .FALSE.
254       CONTINUE
255      CONTINUE
C
C-- SET APPROPRIATE CALL_SUB FLAGS --
C
        IF(CHANGE_VAR(2).OR.CHANGE_VAR(8).OR.CHANGE_VAR(32))THEN
            CALL_SUB(1,2) = .TRUE.
        ENDIF
C
        IF(CHANGE_VAR(2).OR.CHANGE_VAR(3).OR.CHANGE_VAR(6).OR.
     &     CHANGE_VAR(7).OR.CHANGE_VAR(30))THEN
            CALL_SUB(1,3) = .TRUE.
        ENDIF
C
        IF(CHANGE_VAR(2).OR.CHANGE_VAR(20).OR.CHANGE_VAR(21).OR.
     &     CHANGE_VAR(31))THEN
            CALL_SUB(1,4) = .TRUE.
        ENDIF
C
        IF(CHANGE_VAR(2).OR.CHANGE_VAR(15).OR.CHANGE_VAR(16).OR.
     &     CHANGE_VAR(31))THEN
            CALL_SUB(1,5) = .TRUE.
        ENDIF
C
        IF(CHANGE_VAR(2).OR.CHANGE_VAR(9).OR.CHANGE_VAR(11).OR.
     &     CHANGE_VAR(31))THEN
            CALL_SUB(1,6) = .TRUE.
        ENDIF
C
        IF(CHANGE_VAR(2).OR.CHANGE_VAR(25).OR.CHANGE_VAR(26).OR.
     &     CHANGE_VAR(31))THEN
            CALL_SUB(1,7) = .TRUE.
        ENDIF
C
        IF(CHANGE_VAR(2).OR.CHANGE_VAR(33).OR.CHANGE_VAR(34).OR.
     &     CHANGE_VAR(31))THEN
            CALL_SUB(1,8) = .TRUE.
        ENDIF
C
        IF(CALL_SUB(1,2).OR.CALL_SUB(1,3).OR.CALL_SUB(1,4).OR.
     &     CALL_SUB(1,5).OR.CALL_SUB(1,6).OR.CALL_SUB(1,7).OR.
     &     CALL_SUB(1,8))THEN
            CALL_SUB(1,1) = .TRUE.
        ENDIF
C
        IF(CHANGE_VAR(1).OR.CHANGE_VAR(2).OR.CHANGE_VAR(3).OR.
     &     CHANGE_VAR(4).OR.CHANGE_VAR(5).OR.CHANGE_VAR(6).OR.
     &     CHANGE_VAR(7).OR.CHANGE_VAR(8).OR.CHANGE_VAR(30).OR.
     &     CHANGE_VAR(32).OR.CHANGE_VAR(40).OR.CHANGE_VAR(41))THEN
            CALL_SUB(2,1) = .TRUE.
        ENDIF
C
        IF(CHANGE_VAR(1).OR.CHANGE_VAR(2).OR.CHANGE_VAR(9).OR.
     &     CHANGE_VAR(11).OR.CHANGE_VAR(13).OR.CHANGE_VAR(14).OR.
     &     CHANGE_VAR(31).OR.CHANGE_VAR(38).OR.CHANGE_VAR(39))THEN
            CALL_SUB(3,2) = .TRUE.
        ENDIF
C
        IF(CALL_SUB(3,2).OR.CHANGE_VAR(10))THEN
            CALL_SUB(3,1) = .TRUE.
        ENDIF
C
        IF(CHANGE_VAR(1).OR.CHANGE_VAR(2).OR.CHANGE_VAR(25).OR.
     &     CHANGE_VAR(26).OR.CHANGE_VAR(28).OR.CHANGE_VAR(29).OR.
     &     CHANGE_VAR(31))THEN
            CALL_SUB(4,2) = .TRUE.
        ENDIF
C
        IF(CHANGE_VAR(1).OR.CHANGE_VAR(2).OR.CHANGE_VAR(15).OR.
     &     CHANGE_VAR(16).OR.CHANGE_VAR(18).OR.CHANGE_VAR(19).OR.
     &     CHANGE_VAR(31))THEN
            CALL_SUB(4,3) = .TRUE.
        ENDIF
C
        IF(CHANGE_VAR(1).OR.CHANGE_VAR(2).OR.CHANGE_VAR(20).OR.
     &     CHANGE_VAR(21).OR.CHANGE_VAR(23).OR.CHANGE_VAR(24).OR.
     &     CHANGE_VAR(31))THEN
            CALL_SUB(4,4) = .TRUE.
        ENDIF
C
        IF(CHANGE_VAR(1).OR.CHANGE_VAR(2).OR.CHANGE_VAR(33).OR.
     &     CHANGE_VAR(34).OR.CHANGE_VAR(36).OR.CHANGE_VAR(37).OR.
     &     CHANGE_VAR(31))THEN
            CALL_SUB(4,5) = .TRUE.
        ENDIF
C
        IF(CALL_SUB(4,2).OR.CALL_SUB(4,3).OR.CALL_SUB(4,4).OR.
     &     CALL_SUB(4,5).OR.CHANGE_VAR(12).OR.CHANGE_VAR(17).OR.
     &     CHANGE_VAR(22).OR.CHANGE_VAR(27).OR.CHANGE_VAR(35))THEN
            CALL_SUB(4,1) = .TRUE.
        ENDIF
C
        IF(.NOT.WATER_SURFACE) CALL_SUB(1,2) = .FALSE.

        IF(.NOT.SOIL_SURFACE)   CALL_SUB(1,3) = .FALSE.

        IF(.NOT.SNOW_SURFACE)   CALL_SUB(1,9) = .FALSE.

        IF(.NOT.LOG_CONSTITUENT(5)) THEN
            CALL_SUB(1,4) = .FALSE.
            CALL_SUB(4,4) = .FALSE.
        ENDIF

        IF(.NOT.LOG_CONSTITUENT(4)) THEN
            CALL_SUB(1,5) = .FALSE.
            CALL_SUB(4,3) = .FALSE.
        ENDIF

        IF(.NOT.LOG_CONSTITUENT(1)) THEN
            CALL_SUB(1,6) = .FALSE.
            CALL_SUB(3,1) = .FALSE.
            CALL_SUB(3,2) = .FALSE.
        ENDIF

        IF(.NOT.LOG_CONSTITUENT(2)) THEN
            CALL_SUB(1,7) = .FALSE.
            CALL_SUB(4,2) = .FALSE.
        ENDIF

        IF(.NOT.LOG_CONSTITUENT(3)) THEN
            CALL_SUB(1,8) = .FALSE.
            CALL_SUB(4,5) = .FALSE.
        ENDIF
C
        IF(.NOT.(LOG_CONSTITUENT(2).OR.LOG_CONSTITUENT(3).OR.
     &           LOG_CONSTITUENT(4).OR.LOG_CONSTITUENT(5)))
     &           CALL_SUB(4,1) = .FALSE.
C
        IF(LOG_EPS_TABLE(1)) CALL_SUB(1,6) = .FALSE.
        IF(LOG_EPS_TABLE(2)) CALL_SUB(1,7) = .FALSE.
        IF(LOG_EPS_TABLE(3)) CALL_SUB(1,8) = .FALSE.
        IF(LOG_EPS_TABLE(4)) CALL_SUB(1,5) = .FALSE.
        IF(LOG_EPS_TABLE(5)) CALL_SUB(1,4) = .FALSE.
        IF(LOG_EPS_TABLE(6)) THEN
            CALL_SUB(1,2) = .FALSE.
            CALL_SUB(1,3) = .FALSE.
        ENDIF

        IF(.NOT.(CALL_SUB(1,2).OR.CALL_SUB(1,3).OR.CALL_SUB(1,4).OR.
     &           CALL_SUB(1,5).OR.CALL_SUB(1,6).OR.
     &           CALL_SUB(1,7).OR.CALL_SUB(1,8)))THEN
           CALL_SUB(1,1) = .FALSE.
        ENDIF
C
      ENDIF
C
C***********************************************************************
C   SET CONSTANTS FOR THE MODEL
C***********************************************************************
C
        FREQ_HERTZ = FREQ_GHZ*1.0E9
        WAVELENGTH = LIGHT/FREQ_HERTZ
        k0 = 2.0*PI/WAVELENGTH
C
        THETA = THETA_DEGREES*(PI/180.)
        CTHETA = COS(THETA)
        STHETA = SIN(THETA)
C
C***********************************************************************
C   CHECK IF THIS IS THE LAST TIME THROUGH THE LOOP
C***********************************************************************
C
        DO 300 I=1,N_VARIABLES
            IF(STEP_VARIABLE(I))THEN
                IF(LOOP_COUNT(I).LT.LOOP_NUM(I))THEN
                    GO TO 310
                ENDIF
            ENDIF
300     CONTINUE
C
        COMPUTE = .FALSE.
C
310     CONTINUE
C
c        PRINT*,' CALL_SUB(1,1) = ', CALL_SUB(1,1)
c        PRINT*,' CALL_SUB(1,2) = ', CALL_SUB(1,2)
c        PRINT*,' CALL_SUB(1,3) = ', CALL_SUB(1,3)
c        PRINT*,' CALL_SUB(1,4) = ', CALL_SUB(1,4)
c        PRINT*,' CALL_SUB(1,5) = ', CALL_SUB(1,5)
c        PRINT*,' CALL_SUB(1,6) = ', CALL_SUB(1,6)
c        PRINT*,' CALL_SUB(1,7) = ', CALL_SUB(1,7)
c        PRINT*,' CALL_SUB(1,8) = ', CALL_SUB(1,8)
c        PRINT*,' CALL_SUB(1,9) = ', CALL_SUB(1,9)
c        PRINT*,' CALL_SUB(2,1) = ', CALL_SUB(2,1)
c        PRINT*,' CALL_SUB(3,1) = ', CALL_SUB(3,1)
c        PRINT*,' CALL_SUB(3,2) = ', CALL_SUB(3,2)
c        PRINT*,' CALL_SUB(4,1) = ', CALL_SUB(4,1)
c        PRINT*,' CALL_SUB(4,2) = ', CALL_SUB(4,2)
c        PRINT*,' CALL_SUB(4,3) = ', CALL_SUB(4,3)
c        PRINT*,' CALL_SUB(4,4) = ', CALL_SUB(4,4)
c        PRINT*,' CALL_SUB(4,5) = ', CALL_SUB(4,5)
c
        RETURN
        END
C
C***********************************************************************
C***********************************************************************
C23456789012345678901234567890123456789012345678901234567890123456789012
C***********************************************************************
C*** This subroutine steps the value of the indicated model          ***
C*** parameter by its delta value                                    ***
C***    Calling routine:      START_LOOP                             ***
C***    Called subroutines:   none                                   ***
C*********************************************************************** 
C
        SUBROUTINE STEP(J, I_POINT)
        save
C
C***********************************************************************
C----------------------------
c%include 'parameters.include'
        include 'parameters.include'
C----------------------------
C
        INTEGER J, I_POINT
C
        REAL THETA_DEGREES, FREQ_GHZ
        REAL THETA_DELTA, FREQ_DELTA
        REAL T_SOIL, T_WATER, T_VEG
        REAL T_SOIL_DELTA,T_WATER_DELTA,T_VEG_DELTA
        REAL RMS_SOIL, LS_SOIL
        REAL MV_SOIL, SAND, CLAY
        REAL SALT
        REAL RMS_SOIL_DELTA, LS_SOIL_DELTA
        REAL MV_SOIL_DELTA, SAND_DELTA, CLAY_DELTA
        REAL SALT_DELTA
        REAL MG_TRUNK, RHO_TRUNK, TRUNK_DIAM
        REAL MG_TRUNK_DELTA, RHO_TRUNK_DELTA, TRUNK_DIAM_DELTA
        REAL TRUNK_Dsig, TRUNK_Hsig
        REAL TRUNK_Dsig_DELTA, TRUNK_Hsig_DELTA
        REAL DENSITY, CROWN_HGHT, TRUNK_HGHT
        REAL DENSITY_DELTA, CROWN_HGHT_DELTA,TRUNK_HGHT_DELTA
        REAL MG_LEAF,RHO_LEAF,LEAF_DENS,LEAF_DIAM,LEAF_TAU
        REAL MG_LEAF_DELTA, RHO_LEAF_DELTA, LEAF_DENS_DELTA
        REAL LEAF_DIAM_DELTA, LEAF_TAU_DELTA
        REAL MG_NDL, RHO_NDL, NDL_DENS, NDL_DIAM, NDL_LNG
        REAL MG_NDL_DELTA,RHO_NDL_DELTA,NDL_DENS_DELTA
        REAL NDL_DIAM_DELTA, NDL_LNG_DELTA
        REAL MG_BR1, RHO_BR1, BR1_DENS, BR1_DIAM, BR1_LNG
        REAL MG_BR1_DELTA,RHO_BR1_DELTA,BR1_DENS_DELTA
        REAL BR1_DIAM_DELTA, BR1_LNG_DELTA
        REAL MG_BR2, RHO_BR2, BR2_DENS, BR2_DIAM, BR2_LNG
        REAL MG_BR2_DELTA,RHO_BR2_DELTA,BR2_DENS_DELTA
        REAL BR2_DIAM_DELTA, BR2_LNG_DELTA
        REAL T_SNOW, SNOW_T_DELTA
C
        COMMON /R_SENSOR/ THETA_DEGREES, FREQ_GHZ
        COMMON /R_SENSOR_DELTA/ THETA_DELTA, FREQ_DELTA
C
        COMMON /R_ENVIRON/ T_SOIL, T_WATER, T_VEG
        COMMON /R_ENVIRON_DELTA/ T_SOIL_DELTA,T_WATER_DELTA,T_VEG_DELTA
C
        COMMON /R_SURFACE/ RMS_SOIL, LS_SOIL
        COMMON /R_SOIL/ MV_SOIL, SAND, CLAY
        COMMON /R_WATER/ SALT
        COMMON /R_SURFACE_DELTA/ RMS_SOIL_DELTA, LS_SOIL_DELTA
        COMMON /R_SOIL_DELTA/ MV_SOIL_DELTA, SAND_DELTA, CLAY_DELTA
        COMMON /R_WATER_DELTA/ SALT_DELTA
C
        COMMON /R_TRUNK/ MG_TRUNK, RHO_TRUNK, TRUNK_DIAM
        COMMON /R_TRUNK_DELTA/ MG_TRUNK_DELTA, RHO_TRUNK_DELTA, 
     &                         TRUNK_DIAM_DELTA
c
        COMMON /R_TRUNK_SIG/ TRUNK_Dsig, TRUNK_Hsig
        COMMON /TRUNK_SIG_DELTA/ TRUNK_Dsig_DELTA, TRUNK_Hsig_DELTA
C
        COMMON /R_CANOPY/ DENSITY, CROWN_HGHT, TRUNK_HGHT
        COMMON /R_CANOPY_DELTA/ DENSITY_DELTA, CROWN_HGHT_DELTA,
     &                          TRUNK_HGHT_DELTA
C
        COMMON /R_LEAF/ MG_LEAF,RHO_LEAF,LEAF_DENS,LEAF_DIAM,LEAF_TAU
        COMMON /R_LEAF_DELTA/ MG_LEAF_DELTA, RHO_LEAF_DELTA, 
     &                LEAF_DENS_DELTA, LEAF_DIAM_DELTA, LEAF_TAU_DELTA
C
        COMMON /R_NDL/ MG_NDL, RHO_NDL, NDL_DENS, NDL_DIAM, NDL_LNG
        COMMON /R_NDL_DELTA/ MG_NDL_DELTA,RHO_NDL_DELTA,NDL_DENS_DELTA,
     &                        NDL_DIAM_DELTA, NDL_LNG_DELTA
C
        COMMON /R_BR1/ MG_BR1, RHO_BR1, BR1_DENS, BR1_DIAM, BR1_LNG
        COMMON /R_BR1_DELTA/ MG_BR1_DELTA,RHO_BR1_DELTA,BR1_DENS_DELTA,
     &                        BR1_DIAM_DELTA, BR1_LNG_DELTA
C
        COMMON /R_BR2/ MG_BR2, RHO_BR2, BR2_DENS, BR2_DIAM, BR2_LNG
        COMMON /R_BR2_DELTA/ MG_BR2_DELTA,RHO_BR2_DELTA,BR2_DENS_DELTA,
     &                        BR2_DIAM_DELTA, BR2_LNG_DELTA
C
        COMMON /R_SNOW/ T_SNOW
        COMMON /R_SNOW_DELTA/ SNOW_T_DELTA
C
C---------- DECLARATIONS FOR VECTOR INFORMATION ------------------------
C-------- DECLARATIONS FROM SUBROUTINE READ_SENSOR ---------------------
C
        REAL FREQ_VECT(I_VECT_LEN)
        REAL THETA_VECT(I_VECT_LEN)
        LOGICAL LOG_FREQ_TABLE, LOG_THETA_TABLE
        COMMON /R_SENSOR_VECT/ THETA_VECT, FREQ_VECT
        COMMON /L_SENSOR/ LOG_FREQ_TABLE,LOG_THETA_TABLE
C
C-------- DECLARATIONS FROM SUBROUTINE READ_GROUND ---------------------
C
        REAL MV_SOIL_VECT(I_VECT_LEN), RMS_SOIL_VECT(I_VECT_LEN)
        REAL LS_SOIL_VECT (I_VECT_LEN)
        REAL SAND_VECT(I_VECT_LEN), CLAY_VECT(I_VECT_LEN)
        REAL SALT_VECT(I_VECT_LEN)
        REAL SNOW_T_VECT(I_VECT_LEN)
        LOGICAL LOG_MV_SOIL,LOG_ROUGH_SOIL(N_ROUGH_SOIL)
        LOGICAL LOG_SOIL_TEXT,LOG_SALT
        LOGICAL LOG_SNOW_T
        COMMON /R_SURFACE_VECT/ RMS_SOIL_VECT, LS_SOIL_VECT
        COMMON /R_SOIL_VECT/ MV_SOIL_VECT,SAND_VECT,CLAY_VECT
        COMMON /R_WATER_VECT/ SALT_VECT
        COMMON /R_SNOW_VECT/ SNOW_T_VECT
        COMMON /L_SURFACE/ LOG_ROUGH_SOIL
        COMMON /L_SOIL/ LOG_MV_SOIL, LOG_SOIL_TEXT
        COMMON /L_WATER/ LOG_SALT
        COMMON /L_SNOW/ LOG_SNOW_T
C
C-------- DECLARATIONS FROM SUBROUTINE READ_TRUNK ----------------------
C
        REAL MG_TRUNK_VECT(I_VECT_LEN), RHO_TRUNK_VECT(I_VECT_LEN)
        REAL DENSITY_VECT(I_VECT_LEN)
        REAL CROWN_HGHT_VECT(I_VECT_LEN), TRUNK_DIAM_VECT(I_VECT_LEN)
        REAL TRUNK_HGHT_VECT(I_VECT_LEN)
        REAL TRUNK_Dsig_VECT(I_VECT_LEN),  TRUNK_Hsig_VECT(I_VECT_LEN)
        LOGICAL LOG_MG_TRUNK, LOG_RHO_TRUNK, LOG_DENSITY
        LOGICAL LOG_CROWN_HGHT, LOG_TRUNK_DIAM, LOG_TRUNK_HGHT
        LOGICAL LOG_TRUNK_Dsig, LOG_TRUNK_Hsig
        COMMON /R_TRUNK_VECT/MG_TRUNK_VECT, RHO_TRUNK_VECT,
     &                         TRUNK_DIAM_VECT
        COMMON /L_TRUNK/ LOG_MG_TRUNK,LOG_RHO_TRUNK,LOG_TRUNK_DIAM
        COMMON /R_CANOPY_VECT/DENSITY_VECT, CROWN_HGHT_VECT,
     &                        TRUNK_HGHT_VECT
        COMMON /L_CANOPY/ LOG_DENSITY, LOG_CROWN_HGHT, LOG_TRUNK_HGHT
        COMMON /TRUNK_SIG_VECT/ TRUNK_Dsig_VECT,  TRUNK_Hsig_VECT
        COMMON /L_TRUNK_SIG/ LOG_TRUNK_Dsig, LOG_TRUNK_Hsig

C
C-------- DECLARATIONS FROM SUBROUTINE READ_LEAF -----------------------
C
        REAL MG_LEAF_VECT(I_VECT_LEN), RHO_LEAF_VECT(I_VECT_LEN)
        REAL LEAF_DENS_VECT(I_VECT_LEN)
        REAL LEAF_DIAM_VECT(I_VECT_LEN),LEAF_TAU_VECT(I_VECT_LEN)
        LOGICAL LOG_MG_LEAF, LOG_RHO_LEAF, LOG_LEAF_DENS
        LOGICAL LOG_LEAF_DIAM, LOG_LEAF_TAU
        COMMON /R_LEAF_VECT/MG_LEAF_VECT, RHO_LEAF_VECT,
     &              LEAF_DENS_VECT,LEAF_DIAM_VECT, LEAF_TAU_VECT
        COMMON /L_LEAF/ LOG_MG_LEAF, LOG_RHO_LEAF,
     &              LOG_LEAF_DENS, LOG_LEAF_DIAM, LOG_LEAF_TAU
C
C-------- DECLARATIONS FROM SUBROUTINE READ_NEEDLE ---------------------
C
        REAL MG_NDL_VECT(I_VECT_LEN), RHO_NDL_VECT(I_VECT_LEN)
        REAL NDL_DENS_VECT(I_VECT_LEN)
        REAL NDL_DIAM_VECT(I_VECT_LEN), NDL_LNG_VECT(I_VECT_LEN)
        LOGICAL LOG_MG_NDL, LOG_RHO_NDL, LOG_NDL_DENS
        LOGICAL LOG_NDL_DIAM, LOG_NDL_LNG
        COMMON /R_NDL_VECT/MG_NDL_VECT, RHO_NDL_VECT,NDL_DENS_VECT,
     &                      NDL_DIAM_VECT, NDL_LNG_VECT
        COMMON /L_NDL/ LOG_MG_NDL, LOG_RHO_NDL, LOG_NDL_DENS,
     &                      LOG_NDL_DIAM, LOG_NDL_LNG
C
C-------- DECLARATIONS FROM SUBROUTINE READ_PRIMARY_BRANCH -------------
C
        REAL MG_BR1_VECT(I_VECT_LEN), RHO_BR1_VECT(I_VECT_LEN)
        REAL BR1_DENS_VECT(I_VECT_LEN)
        REAL BR1_DIAM_VECT(I_VECT_LEN)
        REAL BR1_LNG_VECT(I_VECT_LEN)
        LOGICAL LOG_MG_BR1, LOG_RHO_BR1, LOG_BR1_DENS
        LOGICAL LOG_BR1_DIAM, LOG_BR1_LNG
        COMMON /R_BR1_VECT/MG_BR1_VECT, RHO_BR1_VECT, BR1_DENS_VECT,
     &                      BR1_DIAM_VECT, BR1_LNG_VECT
        COMMON /L_BR1/ LOG_MG_BR1, LOG_RHO_BR1, LOG_BR1_DENS,
     &                      LOG_BR1_DIAM, LOG_BR1_LNG
C
C-------- DECLARATIONS FROM SUBROUTINE READ_SECONDARY_BRANCH -------------
C
        REAL MG_BR2_VECT(I_VECT_LEN), RHO_BR2_VECT(I_VECT_LEN)
        REAL BR2_DENS_VECT(I_VECT_LEN)
        REAL BR2_DIAM_VECT(I_VECT_LEN)
        REAL BR2_LNG_VECT(I_VECT_LEN)
        LOGICAL LOG_MG_BR2, LOG_RHO_BR2, LOG_BR2_DENS
        LOGICAL LOG_BR2_DIAM, LOG_BR2_LNG
        COMMON /R_BR2_VECT/MG_BR2_VECT, RHO_BR2_VECT, BR2_DENS_VECT,
     &                      BR2_DIAM_VECT, BR2_LNG_VECT
        COMMON /L_BR2/ LOG_MG_BR2, LOG_RHO_BR2, LOG_BR2_DENS,
     &                      LOG_BR2_DIAM, LOG_BR2_LNG
C
C-------- DECLARATIONS FROM SUBROUTINE READ_ENVIRONMENT ----------------
C
        REAL T_SOIL_VECT(I_VECT_LEN), T_WATER_VECT(I_VECT_LEN)
        REAL T_VEG_VECT(I_VECT_LEN)
        LOGICAL LOG_ENVIRONMENT(N_ENVIRONMENT)
        COMMON /R_ENVIRON_VECT/ T_SOIL_VECT, T_WATER_VECT, T_VEG_VECT
        COMMON /L_ENVIRON/ LOG_ENVIRONMENT
C
C--- DECLARATIONS FROM SUBROUTINES READ_EPS_TABLE AND READ_CONFIGURE ---
C
        COMPLEX EPS_TR_VECT(I_VECT_LEN), EPS_LF_VECT(I_VECT_LEN)
        COMPLEX EPS_BR_1_VECT(I_VECT_LEN), EPS_BR_2_VECT(I_VECT_LEN)
        COMPLEX EPS_NDL_VECT(I_VECT_LEN), EPS_GRND_VECT(I_VECT_LEN)
        COMPLEX EPS_SNOW_VECT(I_VECT_LEN)
C
        COMMON /C_EPS_VECT/ EPS_TR_VECT, EPS_LF_VECT, EPS_BR_1_VECT,
     &       EPS_BR_2_VECT, EPS_NDL_VECT, EPS_GRND_VECT, EPS_SNOW_VECT
C
        LOGICAL LOG_CONSTITUENT(N_CONSTITUENTS)
        LOGICAL LOG_EPS_TABLE(N_CONSTITUENTS)
        LOGICAL LOG_DRY_DENSITY(N_CONSTITUENTS) 
        LOGICAL LOG_PDF_TYPE(N_CONSTITUENTS,N_CONST_VARY)
        LOGICAL LOG_HIST(N_CONSTITUENTS)
C
        COMMON /L_CONFIGURE/ LOG_CONSTITUENT, LOG_EPS_TABLE,
     &                       LOG_DRY_DENSITY, LOG_PDF_TYPE, LOG_HIST
C
        INTEGER EPS_NUM(N_EPS)
        COMPLEX EPSILONR(N_EPS),EPSILONRC(N_EPS)
        COMMON /I_EPS_NUM/ EPS_NUM
        COMMON /C_DIELECTRIC/ EPSILONR,EPSILONRC
C
C***********************************************************************
C
        IF(J.EQ.1)THEN
            IF(LOG_THETA_TABLE)THEN
                THETA_DEGREES = THETA_VECT(I_POINT)
            ELSE
                THETA_DEGREES = THETA_DEGREES + THETA_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.2)THEN
            IF(LOG_FREQ_TABLE)THEN
                FREQ_GHZ =  FREQ_VECT(I_POINT)
            ELSE
                FREQ_GHZ = FREQ_GHZ + FREQ_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.3)THEN
            IF(LOG_EPS_TABLE(6))THEN
                EPSILONR(2) = EPS_GRND_VECT(I_POINT)
                EPSILONRC(2) =  CONJG(EPSILONR(2))
            ELSE IF(LOG_MV_SOIL)THEN
                MV_SOIL = MV_SOIL_VECT(I_POINT)
            ELSE
                MV_SOIL = MV_SOIL + MV_SOIL_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.4)THEN
            IF(LOG_ROUGH_SOIL(1))THEN
                RMS_SOIL = RMS_SOIL_VECT(I_POINT)
            ELSE
                RMS_SOIL = RMS_SOIL + RMS_SOIL_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.5)THEN
            IF(LOG_ROUGH_SOIL(2))THEN
                LS_SOIL = LS_SOIL_VECT (I_POINT)
            ELSE
                LS_SOIL = LS_SOIL + LS_SOIL_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.6)THEN
            IF(LOG_SOIL_TEXT)THEN
                SAND =  SAND_VECT(I_POINT)
            ELSE
                SAND = SAND + SAND_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.7)THEN
            IF(LOG_SOIL_TEXT)THEN
                CLAY = CLAY_VECT(I_POINT)
            ELSE
                CLAY = CLAY + CLAY_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.8)THEN
            IF(LOG_SALT) THEN
                SALT = SALT_VECT(I_POINT)
            ELSE
                SALT = SALT + SALT_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.9)THEN
            IF(LOG_EPS_TABLE(1))THEN
                EPSILONR(5) = EPS_TR_VECT(I_POINT)
                EPSILONRC(5) = CONJG(EPSILONR(5))
            ELSE IF(LOG_MG_TRUNK)THEN
                MG_TRUNK = MG_TRUNK_VECT(I_POINT)
            ELSE
                MG_TRUNK = MG_TRUNK + MG_TRUNK_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.10)THEN
            IF(LOG_DENSITY)THEN
                DENSITY = DENSITY_VECT(I_POINT)
            ELSE
                DENSITY = DENSITY + DENSITY_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.11)THEN
            IF(LOG_RHO_TRUNK) THEN
                RHO_TRUNK = RHO_TRUNK_VECT(I_POINT)
            ELSE
                RHO_TRUNK = RHO_TRUNK + RHO_TRUNK_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.12)THEN
            IF(LOG_CROWN_HGHT)THEN
                CROWN_HGHT = CROWN_HGHT_VECT(I_POINT)
            ELSE
                CROWN_HGHT = CROWN_HGHT + CROWN_HGHT_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.13)THEN
            IF(LOG_TRUNK_DIAM)THEN
                TRUNK_DIAM = TRUNK_DIAM_VECT(I_POINT)
            ELSE
                TRUNK_DIAM = TRUNK_DIAM + TRUNK_DIAM_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.14)THEN
            IF(LOG_TRUNK_HGHT)THEN
                TRUNK_HGHT = TRUNK_HGHT_VECT(I_POINT)
            ELSE
                TRUNK_HGHT = TRUNK_HGHT + TRUNK_HGHT_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.15)THEN
            IF(LOG_EPS_TABLE(4))THEN
                EPSILONR(4) = EPS_LF_VECT(I_POINT)
                EPSILONRC(4) = CONJG(EPSILONR(4))
            ELSE IF(LOG_MG_LEAF)THEN
                MG_LEAF = MG_LEAF_VECT(I_POINT)
            ELSE
                MG_LEAF = MG_LEAF + MG_LEAF_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.16)THEN
            IF(LOG_RHO_LEAF)THEN
                RHO_LEAF = RHO_LEAF_VECT(I_POINT)
            ELSE
                RHO_LEAF = RHO_LEAF + RHO_LEAF_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.17)THEN
            IF(LOG_LEAF_DENS)THEN
                LEAF_DENS = LEAF_DENS_VECT(I_POINT)
            ELSE
                LEAF_DENS = LEAF_DENS + LEAF_DENS_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.18)THEN
            IF(LOG_LEAF_DIAM)THEN
                LEAF_DIAM = LEAF_DIAM_VECT(I_POINT)
            ELSE
                LEAF_DIAM = LEAF_DIAM + LEAF_DIAM_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.19)THEN
            IF(LOG_LEAF_TAU)THEN
                LEAF_TAU = LEAF_TAU_VECT(I_POINT)
            ELSE
                LEAF_TAU = LEAF_TAU + LEAF_TAU_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.20)THEN
            IF(LOG_EPS_TABLE(5))THEN
                EPSILONR(3) = EPS_NDL_VECT(I_POINT)
                EPSILONRC(3) = CONJG(EPSILONR(3))
            ELSE IF(LOG_MG_NDL)THEN
                MG_NDL = MG_NDL_VECT(I_POINT)
            ELSE
                MG_NDL = MG_NDL + MG_NDL_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.21)THEN
            IF(LOG_RHO_NDL)THEN
                RHO_NDL = RHO_NDL_VECT(I_POINT)
            ELSE
                RHO_NDL = RHO_NDL + RHO_NDL_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.22)THEN
            IF(LOG_NDL_DENS)THEN
                NDL_DENS = NDL_DENS_VECT(I_POINT)
            ELSE
                NDL_DENS = NDL_DENS + NDL_DENS_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.23)THEN
            IF(LOG_NDL_DIAM)THEN
                NDL_DIAM = NDL_DIAM_VECT(I_POINT)
            ELSE
                NDL_DIAM = NDL_DIAM + NDL_DIAM_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.24)THEN
            IF(LOG_NDL_LNG)THEN
                NDL_LNG = NDL_LNG_VECT(I_POINT)
            ELSE
                NDL_LNG = NDL_LNG + NDL_LNG_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.25)THEN
            IF(LOG_EPS_TABLE(2))THEN
                EPSILONR(6) = EPS_BR_1_VECT(I_POINT)
                EPSILONRC(6) = CONJG(EPSILONR(6))
            ELSE IF(LOG_MG_BR1)THEN
                MG_BR1 = MG_BR1_VECT(I_POINT)
            ELSE
                MG_BR1 = MG_BR1 + MG_BR1_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.26)THEN
            IF(LOG_RHO_BR1)THEN
                RHO_BR1 = RHO_BR1_VECT(I_POINT)
            ELSE
                RHO_BR1 = RHO_BR1 + RHO_BR1_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.27)THEN
            IF(LOG_BR1_DENS)THEN
                BR1_DENS = BR1_DENS_VECT(I_POINT)
            ELSE
                BR1_DENS = BR1_DENS + BR1_DENS_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.28)THEN
            IF(LOG_BR1_DIAM)THEN
                BR1_DIAM = BR1_DIAM_VECT(I_POINT)
            ELSE
                BR1_DIAM = BR1_DIAM + BR1_DIAM_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.29)THEN
            IF(LOG_BR1_LNG)THEN
                BR1_LNG =  BR1_LNG_VECT(I_POINT)
            ELSE
                BR1_LNG = BR1_LNG + BR1_LNG_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.30)THEN
            IF(LOG_ENVIRONMENT(1))THEN
                T_SOIL = T_SOIL_VECT(I_POINT)
            ELSE
                T_SOIL = T_SOIL + T_SOIL_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.31)THEN
            IF(LOG_ENVIRONMENT(3))THEN
                T_VEG = T_VEG_VECT(I_POINT)
            ELSE
                T_VEG = T_VEG + T_VEG_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.32)THEN
            IF(LOG_ENVIRONMENT(2))THEN
                T_WATER = T_WATER_VECT(I_POINT)
            ELSE
                T_WATER = T_WATER + T_WATER_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.33)THEN
            IF(LOG_EPS_TABLE(3))THEN
                EPSILONR(7) = EPS_BR_2_VECT(I_POINT)
                EPSILONRC(7) = CONJG(EPSILONR(7))
            ELSE IF(LOG_MG_BR2)THEN
                MG_BR2 = MG_BR2_VECT(I_POINT)
            ELSE
                MG_BR2 = MG_BR2 + MG_BR2_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.34)THEN
            IF(LOG_RHO_BR2)THEN
                RHO_BR2 = RHO_BR2_VECT(I_POINT)
            ELSE
                RHO_BR2 = RHO_BR2 + RHO_BR2_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.35)THEN
            IF(LOG_BR2_DENS)THEN
                BR2_DENS = BR2_DENS_VECT(I_POINT)
            ELSE
                BR2_DENS = BR2_DENS + BR2_DENS_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.36)THEN
            IF(LOG_BR2_DIAM)THEN
                BR2_DIAM = BR2_DIAM_VECT(I_POINT)
            ELSE
                BR2_DIAM = BR2_DIAM + BR2_DIAM_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.37)THEN
            IF(LOG_BR2_LNG)THEN
                BR2_LNG =  BR2_LNG_VECT(I_POINT)
            ELSE
                BR2_LNG = BR2_LNG + BR2_LNG_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.38)THEN
            IF(LOG_TRUNK_Dsig)THEN
                TRUNK_Dsig = TRUNK_Dsig_VECT(I_POINT)
            ELSE
                TRUNK_Dsig = TRUNK_Dsig + TRUNK_Dsig_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.39)THEN
            IF(LOG_TRUNK_Hsig)THEN
                TRUNK_Hsig = TRUNK_Hsig_VECT(I_POINT)
            ELSE
                TRUNK_Hsig = TRUNK_Hsig + TRUNK_Hsig_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.40)THEN
            IF(LOG_SNOW_T)THEN
                T_SNOW = SNOW_T_VECT(I_POINT)
            ELSE
                T_SNOW =  T_SNOW +  SNOW_T_DELTA
            ENDIF
            RETURN
        ENDIF
C
        IF(J.EQ.41)THEN
            EPSILONR(8) =  EPS_SNOW_VECT(I_POINT)
            EPSILONRC(8) = CONJG(EPSILONR(8))
            RETURN
        ENDIF
C
        CALL WRITE_ERROR(30)
        STOP
        END
C
C***********************************************************************
C***********************************************************************
C23456789012345678901234567890123456789012345678901234567890123456789012
C***********************************************************************
C*** This subroutine resets the value of the indicated model         ***
C*** parameter to its original value                                 ***
C***    Calling routine:      START_LOOP                             ***
C***    Called subroutines:   none                                   ***
C*********************************************************************** 
C
        SUBROUTINE RESET(J)
        save
C
C***********************************************************************
C----------------------------
c%include 'parameters.include'
        include 'parameters.include'
C----------------------------
C
        INTEGER J
C
        REAL FREQ_START,THETA_START
        REAL T_SOIL_START,T_WATER_START,T_VEG_START
        REAL RMS_SOIL_START, LS_SOIL_START
        REAL MV_SOIL_START ,SAND_START , CLAY_START
        REAL SALT_START
        REAL MG_TRUNK_START, RHO_TRUNK_START, TRUNK_DIAM_START 
        REAL DENSITY_START,CROWN_HGHT_START, TRUNK_HGHT_START
        REAL TRUNK_Dsig_START, TRUNK_Hsig_START
        REAL MG_LEAF_START, RHO_LEAF_START
        REAL LEAF_DENS_START,LEAF_DIAM_START,LEAF_TAU_START
        REAL MG_NDL_START, RHO_NDL_START
        REAL NDL_DENS_START, NDL_DIAM_START, NDL_LNG_START
        REAL MG_BR1_START, RHO_BR1_START
        REAL BR1_DENS_START, BR1_DIAM_START, BR1_LNG_START
        REAL MG_BR2_START, RHO_BR2_START
        REAL BR2_DENS_START, BR2_DIAM_START, BR2_LNG_START
        REAL SNOW_T_START
C
        COMMON /SENSOR_START/ FREQ_START,THETA_START
        COMMON /ENV_START/ T_SOIL_START,T_WATER_START,T_VEG_START
        COMMON /SURFACE_START/ RMS_SOIL_START, LS_SOIL_START
        COMMON /SOIL_START/ MV_SOIL_START ,SAND_START , CLAY_START
        COMMON /WATER_START/ SALT_START
        COMMON /TRUNK_START/ MG_TRUNK_START, RHO_TRUNK_START,
     &                       TRUNK_DIAM_START 
        COMMON /TRUNK_SIG_START/ TRUNK_Dsig_START, TRUNK_Hsig_START
        COMMON /CANOPY_START/ DENSITY_START,CROWN_HGHT_START,
     &                        TRUNK_HGHT_START
        COMMON /LEAF_START/ MG_LEAF_START, RHO_LEAF_START,
     &             LEAF_DENS_START,LEAF_DIAM_START,LEAF_TAU_START
        COMMON /NDL_START/ MG_NDL_START, RHO_NDL_START,
     &             NDL_DENS_START, NDL_DIAM_START, NDL_LNG_START
        COMMON /BR1_START/ MG_BR1_START, RHO_BR1_START,
     &            BR1_DENS_START, BR1_DIAM_START, BR1_LNG_START
        COMMON /BR2_START/ MG_BR2_START, RHO_BR2_START,
     &            BR2_DENS_START, BR2_DIAM_START, BR2_LNG_START
        COMMON /SNOW_START/ SNOW_T_START
C
        REAL THETA_DEGREES, FREQ_GHZ
        REAL T_SOIL, T_WATER, T_VEG
        REAL RMS_SOIL, LS_SOIL
        REAL MV_SOIL, SAND, CLAY, SALT
        REAL MG_TRUNK, RHO_TRUNK, TRUNK_DIAM
        REAL DENSITY, CROWN_HGHT, TRUNK_HGHT
        REAL TRUNK_Dsig, TRUNK_Hsig 
        REAL MG_LEAF,RHO_LEAF,LEAF_DENS,LEAF_DIAM,LEAF_TAU
        REAL MG_NDL, RHO_NDL, NDL_DENS, NDL_DIAM, NDL_LNG
        REAL MG_BR1, RHO_BR1, BR1_DENS, BR1_DIAM, BR1_LNG
        REAL MG_BR2, RHO_BR2, BR2_DENS, BR2_DIAM, BR2_LNG
        REAL T_SNOW
C
        COMMON /R_SENSOR/ THETA_DEGREES, FREQ_GHZ
        COMMON /R_ENVIRON/ T_SOIL, T_WATER, T_VEG
        COMMON /R_SURFACE/ RMS_SOIL, LS_SOIL
        COMMON /R_SOIL/ MV_SOIL, SAND, CLAY
        COMMON /R_WATER/ SALT
        COMMON /R_TRUNK/ MG_TRUNK, RHO_TRUNK, TRUNK_DIAM
        COMMON /R_TRUNK_SIG/ TRUNK_Dsig, TRUNK_Hsig 
        COMMON /R_CANOPY/ DENSITY, CROWN_HGHT, TRUNK_HGHT
        COMMON /R_LEAF/ MG_LEAF,RHO_LEAF,LEAF_DENS,LEAF_DIAM,LEAF_TAU
        COMMON /R_NDL/ MG_NDL, RHO_NDL, NDL_DENS, NDL_DIAM, NDL_LNG
        COMMON /R_BR1/ MG_BR1, RHO_BR1, BR1_DENS, BR1_DIAM, BR1_LNG
        COMMON /R_BR2/ MG_BR2, RHO_BR2, BR2_DENS, BR2_DIAM, BR2_LNG
        COMMON /R_SNOW/ T_SNOW
c
        COMPLEX EPS_TR_VECT(I_VECT_LEN) ,EPS_TR_VECT_C(I_VECT_LEN)
        COMPLEX EPS_LF_VECT(I_VECT_LEN) ,EPS_LF_VECT_C(I_VECT_LEN)
        COMPLEX EPS_BR_1_VECT(I_VECT_LEN) ,EPS_BR_1_VECT_C(I_VECT_LEN)
        COMPLEX EPS_BR_2_VECT(I_VECT_LEN) ,EPS_BR_2_VECT_C(I_VECT_LEN)
        COMPLEX EPS_NDL_VECT(I_VECT_LEN) ,EPS_NDL_VECT_C(I_VECT_LEN)
        COMPLEX EPS_GRND_VECT(I_VECT_LEN) ,EPS_GRND_VECT_C(I_VECT_LEN)
        COMPLEX EPS_SNOW_VECT(I_VECT_LEN) , EPS_SNOW_VECT_C(I_VECT_LEN)
C
        COMMON /C_EPS_VECT/ EPS_TR_VECT, EPS_LF_VECT, EPS_BR_1_VECT,
     &       EPS_BR_2_VECT, EPS_NDL_VECT, EPS_GRND_VECT, EPS_SNOW_VECT
        COMMON /C_EPS_VECT_C/ EPS_TR_VECT_C, EPS_LF_VECT_C,
     &              EPS_BR_1_VECT_C, EPS_BR_2_VECT_C, EPS_NDL_VECT_C,
     &              EPS_GRND_VECT_C, EPS_SNOW_VECT_C
c
        COMPLEX EPSILONR(N_EPS),EPSILONRC(N_EPS)
C
        COMMON /C_DIELECTRIC/ EPSILONR,EPSILONRC
c
        LOGICAL LOG_CONSTITUENT(N_CONSTITUENTS)
        LOGICAL LOG_EPS_TABLE(N_CONSTITUENTS)
        LOGICAL LOG_DRY_DENSITY(N_CONSTITUENTS) 
        LOGICAL LOG_PDF_TYPE(N_CONSTITUENTS,N_CONST_VARY)
        LOGICAL LOG_HIST(N_CONSTITUENTS)
C
        COMMON /L_CONFIGURE/ LOG_CONSTITUENT, LOG_EPS_TABLE,
     &                       LOG_DRY_DENSITY, LOG_PDF_TYPE, LOG_HIST

C
C***********************************************************************
C
        IF(J.EQ.1)THEN
            THETA_DEGREES = THETA_START
            RETURN
        ENDIF
C
        IF(J.EQ.2)THEN
            FREQ_GHZ = FREQ_START
            RETURN
        ENDIF
C
        IF(J.EQ.3)THEN
            if(LOG_EPS_TABLE(6))then
                EPSILONR(2) = eps_grnd_vect(1)
                EPSILONRC(2) = conjg(EPSILONR(2))
            else
                MV_SOIL = MV_SOIL_START
            endif
            RETURN
        ENDIF
C
        IF(J.EQ.4)THEN
            RMS_SOIL = RMS_SOIL_START
            RETURN
        ENDIF
C
        IF(J.EQ.5)THEN
            LS_SOIL = LS_SOIL_START
            RETURN
        ENDIF
C
        IF(J.EQ.6)THEN
            SAND  = SAND_START
            RETURN
        ENDIF
C
        IF(J.EQ.7)THEN
            CLAY = CLAY_START
            RETURN
        ENDIF
C
        IF(J.EQ.8)THEN
            SALT = SALT_START
            RETURN
        ENDIF
C
        IF(J.EQ.9)THEN
            if(LOG_EPS_TABLE(1))then
                EPSILONR(5) = eps_tr_vect(1)
                EPSILONRC(5) = conjg(EPSILONR(5))
            else
                MG_TRUNK = MG_TRUNK_START
            endif
            RETURN
        ENDIF
C
        IF(J.EQ.10)THEN
            DENSITY = DENSITY_START
            RETURN
        ENDIF
C
        IF(J.EQ.11)THEN
            RHO_TRUNK = RHO_TRUNK_START
            RETURN
        ENDIF
C
        IF(J.EQ.12)THEN
            CROWN_HGHT = CROWN_HGHT_START
            RETURN
        ENDIF
C
        IF(J.EQ.13)THEN
            TRUNK_DIAM = TRUNK_DIAM_START
            RETURN
        ENDIF
C
        IF(J.EQ.14)THEN
            TRUNK_HGHT = TRUNK_HGHT_START
            RETURN
        ENDIF
C
        IF(J.EQ.15)THEN
            if(LOG_EPS_TABLE(4))then
                EPSILONR(4) = eps_lf_vect(1)
                EPSILONRC(4) = conjg(EPSILONR(4))
            else
                MG_LEAF = MG_LEAF_START
            endif
            RETURN
        ENDIF
C
        IF(J.EQ.16)THEN
            RHO_LEAF = RHO_LEAF_START
            RETURN
        ENDIF
C
        IF(J.EQ.17)THEN
            LEAF_DENS = LEAF_DENS_START
            RETURN
        ENDIF
C
        IF(J.EQ.18)THEN
            LEAF_DIAM = LEAF_DIAM_START
            RETURN
        ENDIF
C
        IF(J.EQ.19)THEN
            LEAF_TAU = LEAF_TAU_START
            RETURN
        ENDIF
C
        IF(J.EQ.20)THEN
            if(LOG_EPS_TABLE(5))then
                EPSILONR(3) = eps_ndl_vect(1)
                EPSILONRC(3) = conjg(EPSILONR(3))
            else
                MG_NDL = MG_NDL_START
            endif
            RETURN
        ENDIF
C
        IF(J.EQ.21)THEN
            RHO_NDL = RHO_NDL_START
            RETURN
        ENDIF
C
        IF(J.EQ.22)THEN
            NDL_DENS = NDL_DENS_START
            RETURN
        ENDIF
C
        IF(J.EQ.23)THEN
            NDL_DIAM = NDL_DIAM_START
            RETURN
        ENDIF
C
        IF(J.EQ.24)THEN
            NDL_LNG = NDL_LNG_START
            RETURN
        ENDIF
C
        IF(J.EQ.25)THEN
            if(LOG_EPS_TABLE(2))then
                EPSILONR(6) = eps_br_1_vect(1)
                EPSILONRC(6) = conjg(EPSILONR(6))
            else
                MG_BR1 = MG_BR1_START
            endif
            RETURN
        ENDIF
C
        IF(J.EQ.26)THEN
            RHO_BR1 = RHO_BR1_START
            RETURN
        ENDIF
C
        IF(J.EQ.27)THEN
            BR1_DENS = BR1_DENS_START
            RETURN
        ENDIF
C
        IF(J.EQ.28)THEN
            BR1_DIAM = BR1_DIAM_START
            RETURN
        ENDIF
C
        IF(J.EQ.29)THEN
            BR1_LNG = BR1_LNG_START
            RETURN
        ENDIF
C
        IF(J.EQ.30)THEN
            T_SOIL = T_SOIL_START
            RETURN
        ENDIF
C
        IF(J.EQ.31)THEN
            T_VEG = T_VEG_START
            RETURN
        ENDIF
C
        IF(J.EQ.32)THEN
            T_WATER = T_WATER_START
            RETURN
        ENDIF
C
        IF(J.EQ.33)THEN
            if(LOG_EPS_TABLE(3))then
                EPSILONR(7) = eps_br_2_vect(1)
                EPSILONRC(7) = conjg(EPSILONR(7))
            else
                MG_BR2 = MG_BR2_START
            endif
            RETURN
        ENDIF
C
        IF(J.EQ.34)THEN
            RHO_BR2 = RHO_BR2_START
            RETURN
        ENDIF
C
        IF(J.EQ.35)THEN
            BR2_DENS = BR2_DENS_START
            RETURN
        ENDIF
C
        IF(J.EQ.36)THEN
            BR2_DIAM = BR2_DIAM_START
            RETURN
        ENDIF
C
        IF(J.EQ.37)THEN
            BR2_LNG = BR2_LNG_START
            RETURN
        ENDIF
C
        IF(J.EQ.38)THEN
            TRUNK_Dsig = TRUNK_Dsig_START
            RETURN
        ENDIF
C
        IF(J.EQ.39)THEN
            TRUNK_Hsig = TRUNK_Hsig_START
            RETURN
        ENDIF
C
        IF(J.EQ.40)THEN
            T_SNOW = SNOW_T_START
            RETURN
        ENDIF
C
        IF(J.EQ.41)THEN
            EPSILONR(8) = EPS_SNOW_VECT(1)
            EPSILONRC(8) = CONJG(EPSILONR(8))
            RETURN
        ENDIF
C
        CALL WRITE_ERROR(35)
        STOP
        END
C
C***********************************************************************
C***********************************************************************
C23456789012345678901234567890123456789012345678901234567890123456789012
C***********************************************************************
C*** This subroutine sets the counter LOOP_NUM() which indicates the ***
C*** number of loops the program must perform through each variable. ***
C***********************************************************************
C
        SUBROUTINE SET_LOOP_COUNTER(LOOP_NUM)
        save
C
C***********************************************************************
C   VARIABLE DECLARATIONS
C***********************************************************************
C----------------------------
c%include 'parameters.include'
        include 'parameters.include'
C----------------------------
C
        INTEGER LOOP_NUM(N_VARIABLES)
C
        INTEGER THETA_NUMBER, FREQ_NUMBER
        INTEGER T_SOIL_NUMBER, T_WATER_NUMBER, T_VEG_NUMBER
        INTEGER RMS_SOIL_NUMBER, LS_SOIL_NUMBER
        INTEGER MV_SOIL_NUMBER, SAND_NUMBER, CLAY_NUMBER
        INTEGER SALT_NUMBER
        INTEGER MG_TRUNK_NUM, RHO_TRUNK_NUM, TRUNK_DIAM_NUM
        INTEGER DENSITY_NUM, CROWN_HGHT_NUM, TRUNK_HGHT_NUM
        INTEGER MG_LEAF_NUM, RHO_LEAF_NUM, LEAF_DENS_NUM   
        INTEGER LEAF_DIAM_NUM, LEAF_TAU_NUM
        INTEGER MG_NDL_NUM, RHO_NDL_NUM, NDL_DENS_NUM
        INTEGER NDL_DIAM_NUM, NDL_LNG_NUM
        INTEGER MG_BR1_NUM, RHO_BR1_NUM, BR1_DENS_NUM   
        INTEGER MG_BR2_NUM, RHO_BR2_NUM, BR2_DENS_NUM   
        INTEGER BR1_DIAM_NUM, BR1_LNG_NUM
        INTEGER BR2_DIAM_NUM, BR2_LNG_NUM
        INTEGER TRUNK_Dsig_NUM, TRUNK_Hsig_NUM
        INTEGER T_SNOW_NUMBER
C
        COMMON /I_SENSOR/ THETA_NUMBER, FREQ_NUMBER
        COMMON /I_ENVIRON/ T_SOIL_NUMBER, T_WATER_NUMBER, T_VEG_NUMBER
        COMMON /I_SURFACE/ RMS_SOIL_NUMBER, LS_SOIL_NUMBER
        COMMON /I_SOIL/ MV_SOIL_NUMBER, SAND_NUMBER, CLAY_NUMBER
        COMMON /I_WATER/ SALT_NUMBER
        COMMON /I_TRUNK/ MG_TRUNK_NUM, RHO_TRUNK_NUM, TRUNK_DIAM_NUM
        COMMON /I_CANOPY/ DENSITY_NUM, CROWN_HGHT_NUM, TRUNK_HGHT_NUM
        COMMON /I_LEAF/ MG_LEAF_NUM, RHO_LEAF_NUM, LEAF_DENS_NUM,   
     &                  LEAF_DIAM_NUM, LEAF_TAU_NUM
        COMMON /I_NDL/ MG_NDL_NUM, RHO_NDL_NUM, NDL_DENS_NUM,
     &                  NDL_DIAM_NUM, NDL_LNG_NUM
        COMMON /I_BR1/ MG_BR1_NUM, RHO_BR1_NUM, BR1_DENS_NUM,   
     &                  BR1_DIAM_NUM, BR1_LNG_NUM
        COMMON /I_BR2/ MG_BR2_NUM, RHO_BR2_NUM, BR2_DENS_NUM,   
     &                  BR2_DIAM_NUM, BR2_LNG_NUM
        COMMON /I_TRUNK_SIG/ TRUNK_Dsig_NUM, TRUNK_Hsig_NUM
        COMMON /I_SNOW/ T_SNOW_NUMBER
C
        LOGICAL LOG_CONSTITUENT(N_CONSTITUENTS)
        LOGICAL LOG_EPS_TABLE(N_CONSTITUENTS)
        LOGICAL LOG_DRY_DENSITY(N_CONSTITUENTS) 
        LOGICAL LOG_PDF_TYPE(N_CONSTITUENTS,N_CONST_VARY)
        LOGICAL LOG_HIST(N_CONSTITUENTS)
C
        COMMON /L_CONFIGURE/ LOG_CONSTITUENT, LOG_EPS_TABLE,
     &                       LOG_DRY_DENSITY, LOG_PDF_TYPE, LOG_HIST
C
        INTEGER EPS_NUM(N_EPS)
C
        COMMON /I_EPS_NUM/ EPS_NUM
C
C***********************************************************************
C   SET THE COUNTER
C***********************************************************************
C
        LOOP_NUM(1)  = THETA_NUMBER
        LOOP_NUM(2)  = FREQ_NUMBER

        IF(LOG_EPS_TABLE(6))THEN
            LOOP_NUM(3)  = EPS_NUM(2)
            LOOP_NUM(6)  = 1
            LOOP_NUM(7)  = 1
            LOOP_NUM(8)  = 1
            LOOP_NUM(30) = 1
            LOOP_NUM(32) = 1
        ELSE
            LOOP_NUM(3)  = MV_SOIL_NUMBER
            LOOP_NUM(6)  = SAND_NUMBER
            LOOP_NUM(7)  = CLAY_NUMBER
            LOOP_NUM(8)  = SALT_NUMBER
            LOOP_NUM(30) = T_SOIL_NUMBER
            LOOP_NUM(32) = T_WATER_NUMBER
        ENDIF

        LOOP_NUM(4)  = RMS_SOIL_NUMBER
        LOOP_NUM(5)  = LS_SOIL_NUMBER

        IF(LOG_EPS_TABLE(1))THEN
            LOOP_NUM(9)  = EPS_NUM(5)
            LOOP_NUM(11) = 1
        ELSE
            LOOP_NUM(9)  = MG_TRUNK_NUM
            LOOP_NUM(11) = RHO_TRUNK_NUM
        ENDIF

        LOOP_NUM(10) = DENSITY_NUM
        LOOP_NUM(12) = CROWN_HGHT_NUM
        LOOP_NUM(13) = TRUNK_DIAM_NUM
        LOOP_NUM(14) = TRUNK_HGHT_NUM

        IF(LOG_EPS_TABLE(4))THEN
            LOOP_NUM(15) = EPS_NUM(4)
            LOOP_NUM(16) = 1
        ELSE
            LOOP_NUM(15) = MG_LEAF_NUM
            LOOP_NUM(16) = RHO_LEAF_NUM
        ENDIF

        LOOP_NUM(17) = LEAF_DENS_NUM   
        LOOP_NUM(18) = LEAF_DIAM_NUM
        LOOP_NUM(19) = LEAF_TAU_NUM

        IF(LOG_EPS_TABLE(5))THEN
            LOOP_NUM(20) = EPS_NUM(3)
            LOOP_NUM(21) = 1
        ELSE
            LOOP_NUM(20) = MG_NDL_NUM
            LOOP_NUM(21) = RHO_NDL_NUM
        ENDIF

        LOOP_NUM(22) = NDL_DENS_NUM
        LOOP_NUM(23) = NDL_DIAM_NUM
        LOOP_NUM(24) = NDL_LNG_NUM

        IF(LOG_EPS_TABLE(2))THEN
            LOOP_NUM(25) = EPS_NUM(6)
            LOOP_NUM(26) = 1
        ELSE
            LOOP_NUM(25) = MG_BR1_NUM
            LOOP_NUM(26) = RHO_BR1_NUM
        ENDIF

        LOOP_NUM(27) = BR1_DENS_NUM   
        LOOP_NUM(28) = BR1_DIAM_NUM
        LOOP_NUM(29) = BR1_LNG_NUM
        LOOP_NUM(31) = T_VEG_NUMBER

        IF(LOG_EPS_TABLE(3))THEN
            LOOP_NUM(33) = EPS_NUM(7)
            LOOP_NUM(34) = 1
        ELSE
            LOOP_NUM(33) = MG_BR2_NUM
            LOOP_NUM(34) = RHO_BR2_NUM
        ENDIF

        LOOP_NUM(35) = BR2_DENS_NUM   
        LOOP_NUM(36) = BR2_DIAM_NUM
        LOOP_NUM(37) = BR2_LNG_NUM

        LOOP_NUM(38) = TRUNK_Dsig_NUM
        LOOP_NUM(39) = TRUNK_Hsig_NUM

        LOOP_NUM(40) = T_SNOW_NUMBER
        LOOP_NUM(41) = EPS_NUM(8)
C
        RETURN
        END
C
C***********************************************************************
