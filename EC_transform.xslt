<?xml version='1.0'?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" />

	<xsl:template name="convert_date">
		<xsl:param name="datesrc" />
		<xsl:value-of
			select='concat("20",substring($datesrc,5,2), "-", substring($datesrc,3,2), "-", substring($datesrc,1,2))' />
	</xsl:template>

	<xsl:template match="/">
		<xsl:apply-templates select="EC" />
	</xsl:template>

	<xsl:template match="EC">
		<CBIEnvelBkToCstmrStmtReqLogMsg>
			<CBIBkToCstmrStmtReqLogMsg>
				<CBIPrdcStmtReqLogMsg>
					<GrpHdr>
						<MsgId>
							<xsl:value-of select="support_name" />
						</MsgId>
						<CreDtTm>
							<xsl:call-template name="convert_date">
								<xsl:with-param name="datesrc" select="creation_date" />
							</xsl:call-template>
						</CreDtTm>
						<MsgRcpt>
							<Id>
								<OrgId>
									<Othr>
										<Id>
											<xsl:value-of select="recipient" />
										</Id>
									</Othr>
								</OrgId>
							</Id>
						</MsgRcpt>
						<AddtlInf>
							<xsl:value-of select="sender" />
						</AddtlInf>
					</GrpHdr>


					<xsl:for-each select="x61">
						<Stmt>
							<Id>
								<xsl:value-of select="/EC/stmt_id" />
							</Id>
							<ElctrncSeqNb>
								<xsl:value-of select="number" />
							</ElctrncSeqNb>
							<CreDtTm>
								<xsl:call-template name="convert_date">
									<xsl:with-param name="datesrc" select="/EC/creation_date" />
								</xsl:call-template>
							</CreDtTm>
							<FrToDt>
								<FrDtTm>
									<xsl:call-template name="convert_date">
										<xsl:with-param name="datesrc" select="accounting_date" />
									</xsl:call-template>
								</FrDtTm>
								<ToDtTm>
									<xsl:call-template name="convert_date">
										<xsl:with-param name="datesrc" select="x64/accounting_date" />
									</xsl:call-template>
								</ToDtTm>
							</FrToDt>

							<CpyDplctInd>
								<xsl:if test="reason = 93013">
									DUPL
								</xsl:if>
							</CpyDplctInd>
							<Acct>
								<Id>
									<IBAN>
										<xsl:value-of select="iban" />
									</IBAN>
								</Id>
								<Ccy>
									<xsl:value-of select="currency_code" />
								</Ccy>
								<Tp>
									<Prtry>
										<xsl:value-of select="account_type" />
									</Prtry>
								</Tp>
								<Nm>
									<xsl:value-of select="description" />
								</Nm>
								<Svcr>
									<FinInstnId>
										<ClrSysMmbId>
											<MmbId>
												<xsl:value-of select="abi" />
											</MmbId>
										</ClrSysMmbId>
									</FinInstnId>
								</Svcr>
							</Acct>

							<Bal>
								<Tp>
									<CdOrPrtry>
										<Cd>OPBD</Cd>
									</CdOrPrtry>
								</Tp>
								<Amt>
									<xsl:value-of select="opening_balance" />
								</Amt>
								<CdtDbtInd>
									<xsl:if test="sign = 'C' or sign = 'c'">
										CRDT
									</xsl:if>
									<xsl:if test="sign = 'D' or sign = 'd'">
										DBIT
									</xsl:if>
								</CdtDbtInd>
								<Dt>
									<Dt>
										<xsl:call-template name="convert_date">
											<xsl:with-param name="datesrc" select="accounting_date" />
										</xsl:call-template>
									</Dt>
								</Dt>
							</Bal>
							<xsl:if test="x64">
								<Bal>
									<Tp>
										<CdOrPrtry>
											<Cd>CLBD</Cd>
										</CdOrPrtry>
									</Tp>
									<Amt>
										<xsl:value-of select="x64/accounts_balance" />
									</Amt>
									<CdtDbtInd>
										<xsl:if
											test="x64/accounts_balance_sign = 'C' or x64/accounts_balance_sign = 'c'">
											CRDT
										</xsl:if>
										<xsl:if
											test="x64/accounts_balance_sign = 'D' or x64/accounts_balance_sign = 'd'">
											DBIT
										</xsl:if>
									</CdtDbtInd>
									<Dt>
										<Dt>
											<xsl:call-template name="convert_date">
												<xsl:with-param name="datesrc" select="x64/accounting_date" />
											</xsl:call-template>
										</Dt>
									</Dt>
								</Bal>

							</xsl:if>

							<TxsSummry>
								<TtlNtries>
									<NbOfNtries>
										<xsl:value-of select="count(x62)" />
									</NbOfNtries>
								</TtlNtries>
							</TxsSummry>
							<xsl:for-each select="x62">
								<Ntry>
									<NtryRef>
										<xsl:value-of select="number" />
									</NtryRef>
									<Amt>
										<xsl:value-of select="amount" />
									</Amt>
									<CdtDbtInd>
										<xsl:if test="sign = 'C' or sign = 'c'">
											CRDT
										</xsl:if>
										<xsl:if test="sign = 'D' or sign = 'd'">
											DBIT
										</xsl:if>
									</CdtDbtInd>
									<Sts>BOOK</Sts>
									<BookgDt>
										<Dt>
											<xsl:call-template name="convert_date">
												<xsl:with-param name="datesrc" select="account_date" />
											</xsl:call-template>
										</Dt>
									</BookgDt>
									<ValDt>
										<Dt>
											<xsl:call-template name="convert_date">
												<xsl:with-param name="datesrc" select="date" />
											</xsl:call-template>
										</Dt>
									</ValDt>
									<AcctSvcrRef>
										<xsl:value-of select="bank_reference" />
									</AcctSvcrRef>
									<BkTxCd>
										<Prtry>
											<Cd>
												<xsl:value-of select="reason_internal_reason" />
											</Cd>
										</Prtry>
									</BkTxCd>
									<xsl:if test="x63[@structure_flag='ZZ1']">
										<AmtDtls>
											<InstdAmt>
												<Amt>
													<xsl:attribute name="ccy"><xsl:value-of
														select="x63[@structure_flag='ZZ1']/currency_code" /></xsl:attribute>
													<xsl:value-of select="x63[@structure_flag='ZZ1']/amount" />
												</Amt>
											</InstdAmt>
											<TxAmt>
												<Amt>
													<xsl:attribute name="ccy"><xsl:value-of
														select="x63[@structure_flag='ZZ1']/currency_code_paid" /></xsl:attribute>
													<xsl:value-of select="x63[@structure_flag='ZZ1']/paid" />
												</Amt>
												<CcyXchg>
													<xchgRate>
														<xsl:value-of select="x63[@structure_flag='ZZ1']/exchange_rate" />
													</xchgRate>
												</CcyXchg>
											</TxAmt>
											<CntrValAmt>
												<Amt>
													<xsl:attribute name="ccy"><xsl:value-of
														select="x63[@structure_flag='ZZ1']/currency_code_transacted" /></xsl:attribute>
													<xsl:value-of select="x63[@structure_flag='ZZ1']/transacted" />
												</Amt>
											</CntrValAmt>
										</AmtDtls>
									</xsl:if>
									<NtryDtls>
										<Btch>
											<MsgId>
												<xsl:value-of select="x63[@structure_flag='ID1']/msg_id" />
											</MsgId>
											<PmtInfId>
												<xsl:value-of select="customer_movement_description" />
											</PmtInfId>

										</Btch>
										<TxDtls>
											<Refs>
												<MsgId>
													<xsl:value-of select="x63[@structure_flag='ID1']/msg_id" />
												</MsgId>
												<PmtInfId>
													<xsl:value-of select="customer_movement_description" />
												</PmtInfId>
												<EndToEndId>
													<xsl:value-of select="x63[@structure_flag='ID1']/end_to_end_id" />
												</EndToEndId>
												<ChqNb>
													<xsl:value-of select="cheque_number" />
												</ChqNb>

											</Refs>
											<RltdPties>
												<InitgPty>
													<Nm>
														<xsl:value-of
															select="x63[@structure_flag='YYY']/ordering_description" />
														<xsl:value-of select="x63[@structure_flag='ZZ2']/ordering_party" />
													</Nm>
													<Id>
														<OrgId>
															<Othr>
																<Id>
																	<xsl:value-of
																		select="x63[@structure_flag='YYY']/ordering_code" />
																</Id>
															</Othr>
														</OrgId>
													</Id>
												</InitgPty>
												<Cdtr>
													<xsl:value-of select="x63[@structure_flag='ZZ3']/payee" />
												</Cdtr>

											</RltdPties>
											<RmtInf>
												<Ustrd>
													<xsl:value-of
														select="x63[@structure_flag='RI1']/reconciliation_data" />
													<xsl:value-of
														select="x63[@structure_flag='RI2']/reconciliation_data" />
												</Ustrd>
											</RmtInf>
											<RtrInf>
												<AddtlInf>
													<xsl:value-of select="x63[@structure_flag='ZZ3']/payment_reason" />
													<xsl:value-of select="x63[not(@structure_flag)]/description" />
												</AddtlInf>
											</RtrInf>
										</TxDtls>
									</NtryDtls>
								</Ntry>
							</xsl:for-each>
						</Stmt>
					</xsl:for-each>
				</CBIPrdcStmtReqLogMsg>
			</CBIBkToCstmrStmtReqLogMsg>
		</CBIEnvelBkToCstmrStmtReqLogMsg>
	</xsl:template>
</xsl:stylesheet>