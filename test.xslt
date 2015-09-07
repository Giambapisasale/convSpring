<?xml version='1.0'?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" />
	<xsl:template match="/">
		<xsl:apply-templates select="RH" />
	</xsl:template>

	<xsl:template match="RH">
		<CBIDlyStmtReqLogMsg>
			<GrpHdr>
				<MsgId>
					<xsl:value-of select="support_name" />
				</MsgId>
				<CreDtTm>
					<xsl:value-of select="creation_date" />
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
						<xsl:value-of select="/RH/stmt_id" />
					</Id>
					<ElctrncSeqNb>
						<xsl:value-of select="number" />
					</ElctrncSeqNb>
					<CreDtTm>
						<xsl:value-of select="/RH/creation_date" />
					</CreDtTm>
					<CpyDplctInd>
						<xsl:if test="reason = 93011">
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
								<Cd>CLAV</Cd>
							</CdOrPrtry>
						</Tp>
						<Amt Ccy="">0.0</Amt>
						<CdtDbtInd>CRDT</CdtDbtInd>
						<Dt>
							<Dt>2001-01-01</Dt>
						</Dt>
					</Bal>
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
								<xsl:if test="sign = C or sign = c">
									CRDT
								</xsl:if>
								<xsl:if test="sign = D or sign = d">
									DBIT
								</xsl:if>
							</CdtDbtInd>
							<Sts>BOOK</Sts>
							<BookgDt>
								<Dt>
									<xsl:value-of select="account_date" />
								</Dt>
							</BookgDt>
							<ValDt>
								<Dt>
									<xsl:value-of select="date" />
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
															<xsl:value-of select="x63[@structure_flag='YYY']/ordering_code" />
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
					<AddtlStmtInf>AddtlStmtInf</AddtlStmtInf>
				</Stmt>
			</xsl:for-each>
		</CBIDlyStmtReqLogMsg>
	</xsl:template>
</xsl:stylesheet>